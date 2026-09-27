package com.realm.auth_service.role.repository;

import com.realm.auth_service.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select e from RoleEntity e where e.name = :name and e.valid = true")
    Optional<RoleEntity> findByName(String name);
}