package com.realm.auth_service.user.service.impl;

import com.realm.auth_service.role.entity.RoleEntity;
import com.realm.auth_service.user.details.AppUserDetails;
import com.realm.auth_service.user.dto.UserDto;
import com.realm.auth_service.user.entity.UserEntity;
import com.realm.auth_service.user.service.UserService;
import com.realm.jwt_security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private UserDetails authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return userDetailsService.loadUserByUsername(username);
    }


    @Override
    public UserDto login(String username, String password) {
        UserEntity user = ((AppUserDetails) authenticate(username, password)).getEntity();
        String accessToken = generateAccessToken(user);
        UUID refreshToken = generateRefreshToken(user);

        // generate refresh token cookies for validate only

        // generate access token cookies for all

        return null;
    }

    private String generateAccessToken(UserEntity user) {
        List<String> roles = user.getRoles().stream().map(RoleEntity::getName).toList();
        return jwtUtil.generateToken(
                user.getEmail(),
                Map.of("currentRole", user.getCurrentRole().getName(), "roles", roles)
        );
    }

    private UUID generateRefreshToken(UserEntity user) {
        return null;
    }
}
