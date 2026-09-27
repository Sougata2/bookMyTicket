package com.realm.auth_service.auth.service;

import com.realm.auth_service.auth.dto.AuthDto;
import com.realm.auth_service.auth.dto.RegistrationDto;
import com.realm.auth_service.user.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthDto login(String username, String password, HttpServletResponse response);

    void register(RegistrationDto dto);
}
