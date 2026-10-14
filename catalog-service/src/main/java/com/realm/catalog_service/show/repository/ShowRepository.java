package com.realm.catalog_service.show.repository;

import com.realm.catalog_service.show.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Long> {
}