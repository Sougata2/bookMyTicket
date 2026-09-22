package com.realm.auth_service.role.dto;

import com.realm.auth_service.utils.AuditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.realm.auth_service.role.entity.RoleEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends AuditDto implements Serializable {
    private Long id;
    private String name;
}