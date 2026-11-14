package com.realm.booking_service.client.catalog;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@FeignClient(name = "catalog-service")
public interface CatalogClient {
    @GetMapping("/catalog/shows/{id}")
    ShowDto getShow(@PathVariable Long id);

    @GetMapping("/catalog/shows/{id}/seats")
    List<SeatDto> getSeats(@PathVariable Long id);

    enum SeatStatus {
        AVAILABLE,
        LOCKED,
        BOOKED
    }

    record VenueDto(
            Long id,
            String name,
            String city,
            String address
    ) {
    }

    record ScreenDto(
            Long id,
            VenueDto venue,
            String name,
            Integer totalRows,
            Integer seatsPerRow
    ) {
    }

    record EventDto(
            Long id,
            String title,
            String description,
            String category,
            String language,
            Integer durationMins,
            String posterUrl
    ) {
    }

    record ShowDto(
            Long id,
            EventDto event,
            ScreenDto screen,
            Instant startTime,
            BigDecimal basePrice
    ) {
    }

    record SeatDto(
            Long id,
            ShowDto show,
            String rowLabel,
            int seatNumber,
            SeatStatus status,
            BigDecimal price,
            Long version
    ) {
    }
}
