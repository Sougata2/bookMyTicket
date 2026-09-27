package com.realm.auth_service.auth.dto;

import com.realm.auth_service.user.dto.UserDto;
import com.realm.auth_service.utils.AuditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.realm.auth_service.auth.entity.RefreshTokenEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto extends AuditDto implements Serializable {
    private Long id;
    private UUID token;
    private Instant expiry;
    private boolean revoked;
    private UserDto user;
}