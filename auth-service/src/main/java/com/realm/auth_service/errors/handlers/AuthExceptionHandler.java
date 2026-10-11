package com.realm.auth_service.errors.handlers;

import com.realm.auth_service.errors.exceptions.ExpiredTokenException;
import com.realm.auth_service.errors.exceptions.RevokedTokenUseException;
import com.realm.auth_service.errors.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(RevokedTokenUseException.class)
    public ResponseEntity<ErrorResponseDto> handleRevokedTokenUseException(RevokedTokenUseException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.builder()
                        .code(HttpStatus.UNAUTHORIZED)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredTokenException(ExpiredTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.builder()
                        .code(HttpStatus.UNAUTHORIZED)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
