package com.realm.auth_service.user.dto;

import com.realm.auth_service.utils.AuditDto;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.realm.auth_service.user.entity.UserEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AuditDto implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
}