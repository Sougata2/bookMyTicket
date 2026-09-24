package com.realm.auth_service.user.service;


import com.realm.auth_service.user.dto.UserDto;

public interface UserService {
    UserDto login(String username, String password);
}
