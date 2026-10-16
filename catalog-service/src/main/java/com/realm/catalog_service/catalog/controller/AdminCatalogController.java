package com.realm.catalog_service.catalog.controller;

import com.realm.catalog_service.catalog.service.AdminCatalogService;
import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.screen.dto.ScreenDto;
import com.realm.catalog_service.show.dto.ShowDto;
import com.realm.catalog_service.venue.dto.VenueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/catalog")
public class AdminCatalogController {
    private final AdminCatalogService catalogService;

    @PostMapping("/create-venue")
    public ResponseEntity<VenueDto> createVenue(@RequestBody VenueDto dto) {
        return ResponseEntity.ok(catalogService.createVenue(dto));
    }

    @PostMapping("/create-screen")
    public ResponseEntity<ScreenDto> createScreen(@RequestBody ScreenDto dto) {
        return ResponseEntity.ok(catalogService.createScreen(dto));
    }

    @PostMapping("/create-event")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto dto) {
        return ResponseEntity.ok(catalogService.createEvent(dto));
    }

    @PostMapping("/create-show")
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto dto) {
        return ResponseEntity.ok(catalogService.createShow(dto));
    }
}
