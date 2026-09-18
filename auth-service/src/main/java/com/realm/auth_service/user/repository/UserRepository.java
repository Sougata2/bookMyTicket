package com.realm.auth_service.user.repository;

import com.realm.auth_service.user.entity.UserEntity;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select e from UserEntity e where e.email = :email and e.valid = true")
    Optional<UserEntity> findByEmail(String email);
}