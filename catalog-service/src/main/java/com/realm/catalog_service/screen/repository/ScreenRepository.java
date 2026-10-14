package com.realm.catalog_service.screen.repository;

import com.realm.catalog_service.screen.entity.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenEntity, Long> {
}