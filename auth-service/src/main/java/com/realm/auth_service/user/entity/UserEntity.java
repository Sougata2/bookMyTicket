package com.realm.auth_service.user.entity;

import com.realm.auth_service.role.entity.RoleEntity;
import com.realm.auth_service.utils.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String fullName;

    @ManyToMany
    @JoinTable(name = "user_role")
    private List<RoleEntity> roles;

    @ManyToOne
    @JoinColumn(name = "current_role_id")
    private RoleEntity currentRole;

    @PrePersist
    protected void prePersist() {
        this.password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(this.password);
    }
}
