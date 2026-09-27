package com.realm.auth_service.auth.controller;

import com.realm.auth_service.auth.dto.AuthDto;
import com.realm.auth_service.auth.dto.RegistrationDto;
import com.realm.auth_service.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
