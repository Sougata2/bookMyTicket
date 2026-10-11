package com.realm.auth_service.errors.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
    private HttpStatus code;
    private String message;
    private LocalDateTime timestamp;
}
