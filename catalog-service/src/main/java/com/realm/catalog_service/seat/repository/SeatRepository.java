package com.realm.catalog_service.seat.repository;

import com.realm.catalog_service.seat.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    @Query("select e from SeatEntity e where e.show.id = :showId order by e.rowLabel asc , e.seatNumber asc")
    List<SeatEntity> findSeatsByShowId(Long showId);
}