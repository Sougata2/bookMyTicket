package com.realm.auth_service.user.dto;

import com.realm.auth_service.role.dto.RoleDto;
import com.realm.auth_service.utils.AuditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
    private List<RoleDto> roles;
    private RoleDto currentRole;
}