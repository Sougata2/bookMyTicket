package com.realm.auth_service.auth.service.impl;

import com.realm.auth_service.auth.dto.AuthDto;
import com.realm.auth_service.auth.dto.RegistrationDto;
import com.realm.auth_service.auth.entity.RefreshTokenEntity;
import com.realm.auth_service.auth.repository.RefreshTokenRepository;
import com.realm.auth_service.auth.service.AuthService;
import com.realm.auth_service.role.entity.RoleEntity;
import com.realm.auth_service.role.repository.RoleRepository;
import com.realm.auth_service.user.details.AppUserDetails;
import com.realm.auth_service.user.entity.UserEntity;
import com.realm.auth_service.user.repository.UserRepository;
import com.realm.jwt_security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    private UserDetails authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return userDetailsService.loadUserByUsername(username);
    }

    @Override
    public AuthDto login(String username, String password, HttpServletResponse response) {
        UserEntity user = ((AppUserDetails) authenticate(username, password)).getEntity();
        String accessToken = generateAccessToken(user);
        UUID refreshToken = generateRefreshToken(user);

        // generate refresh token cookies for validate only
        ResponseCookie refreshCookies = ResponseCookie.from("REFRESH_TOKEN", refreshToken.toString())
                .secure(true)
                .httpOnly(true)
                .sameSite("Strict")
                .path("/auth/refresh")
                .maxAge(jwtUtil.getRefreshSeconds())
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookies.toString());
        AuthDto auth = new AuthDto();
        auth.setAccessToken(accessToken);
        auth.setEmail(user.getEmail());
        auth.setFirstName(user.getFirstName());
        auth.setLastName(user.getLastName());
        auth.setMiddleName(user.getMiddleName());
        auth.setPassword(null);
        auth.setFullName(user.getFullName());
        return auth;
    }

    @Override
    public void register(RegistrationDto dto) {
        String fullName = String.join(
                " ",
                Stream.of(
                                dto.getFirstName(),
                                dto.getMiddleName(),
                                dto.getLastName()
                        )
                        .filter(s -> s != null && !s.isBlank())
                        .toList()
        );
        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setFullName(fullName);

        RoleEntity role = roleRepository.findByName("USER").orElseThrow(() -> new EntityNotFoundException("Role %s not found".formatted("USER")));
        user.setCurrentRole(role);
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    private String generateAccessToken(UserEntity user) {
        return jwtUtil.generateToken(
                user.getEmail(),
                Map.of("currentRole", user.getCurrentRole().getName())
        );
    }

    private UUID generateRefreshToken(UserEntity user) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setToken(UUID.randomUUID());
        refreshTokenEntity.setUser(user);
        refreshTokenEntity.setExpiry(Instant.now().plusSeconds(jwtUtil.getRefreshSeconds()));
        refreshTokenEntity.setRevoked(false);
        RefreshTokenEntity saved = refreshTokenRepository.save(refreshTokenEntity);
        return saved.getToken();
    }
}
