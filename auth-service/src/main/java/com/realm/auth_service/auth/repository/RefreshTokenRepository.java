package com.realm.auth_service.auth.repository;

import com.realm.auth_service.auth.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    @Query("select e from RefreshTokenEntity e where e.token = :token and e.valid = true")
    Optional<RefreshTokenEntity> findByToken(UUID token);
}