package com.realm.auth_service.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
    private Boolean valid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
