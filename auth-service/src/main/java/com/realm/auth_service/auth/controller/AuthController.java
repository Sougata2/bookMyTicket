package com.realm.auth_service.auth.controller;

import com.realm.auth_service.auth.dto.AuthDto;
import com.realm.auth_service.auth.dto.RegistrationDto;
import com.realm.auth_service.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegistrationDto dto) {
        service.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody AuthDto dto, HttpServletResponse response) {
        return ResponseEntity.ok(service.login(dto.getEmail(), dto.getPassword(), response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthDto> refresh(@CookieValue(value = "REFRESH_TOKEN") String refreshToken, HttpServletResponse response) {
        return ResponseEntity.ok(service.refresh(refreshToken, response));
    }
}
