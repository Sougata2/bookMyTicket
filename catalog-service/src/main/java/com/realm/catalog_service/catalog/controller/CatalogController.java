package com.realm.catalog_service.catalog.controller;

import com.realm.catalog_service.catalog.dto.PageResponse;
import com.realm.catalog_service.catalog.service.CatalogQueryService;
import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.seat.dto.SeatDto;
import com.realm.catalog_service.show.dto.ShowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogQueryService service;

    @GetMapping("/events")
    public PageResponse<EventDto> events(
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return service.listEvents(city, page, size);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventDto> event(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEvent(id));
    }

    @GetMapping("/events/{id}/shows")
    public ResponseEntity<List<ShowDto>> showsForEvent(@PathVariable Long id, @RequestParam(required = false) String city) {
        return ResponseEntity.ok(service.upcomingShows(id, city));
    }

    @GetMapping("/shows/{id}")
    public ResponseEntity<ShowDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.getShow(id));
    }

    @GetMapping("/shows/{id}/seats")
    public ResponseEntity<List<SeatDto>> seat(@PathVariable Long id) {
        return ResponseEntity.ok(service.findSeatsByShowId(id));
    }
}
