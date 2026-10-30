package com.realm.catalog_service.show.repository;

import com.realm.catalog_service.show.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Long> {
    @Query("""
            select e from ShowEntity e
            where e.event.id = :eventId
            and e.startTime > :now
            order by e.startTime
            """)
    List<ShowEntity> getUpcomingShows(Long eventId, Instant now);

    @Query("""
            select e from ShowEntity e
            where e.event.id = :eventId
            and e.startTime > :now
            and lower(e.screen.venue.city) = lower(:city)
            order by
            e.startTime
            """)
    List<ShowEntity> getUpcomingShows(Long eventId, String city);
}