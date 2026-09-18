package com.realm.auth_service.user.entity;

import com.realm.auth_service.utils.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

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

    @PrePersist
    protected void prePersist() {
        this.password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(this.password);
    }
}
