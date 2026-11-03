package com.realm.catalog_service.event.repository;

import com.realm.catalog_service.event.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query("""
            select e from EventEntity e
            where exists (
                select 1 from ShowEntity s
                where s.event.id = e.id
                and s.startTime > :now
                and lower(s.screen.venue.city) = lower(:city)
            )
            """)
    Page<EventEntity> findNowShowingInCity(String city, Instant now, Pageable pageable);
}