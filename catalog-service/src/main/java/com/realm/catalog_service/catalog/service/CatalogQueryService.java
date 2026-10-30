package com.realm.catalog_service.catalog.service;

import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.event.entity.EventEntity;
import com.realm.catalog_service.event.mapper.EventMapper;
import com.realm.catalog_service.event.repository.EventRepository;
import com.realm.catalog_service.seat.dto.SeatDto;
import com.realm.catalog_service.seat.mapper.SeatMapper;
import com.realm.catalog_service.seat.repository.SeatRepository;
import com.realm.catalog_service.show.dto.ShowDto;
import com.realm.catalog_service.show.entity.ShowEntity;
import com.realm.catalog_service.show.mapper.ShowMapper;
import com.realm.catalog_service.show.repository.ShowRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogQueryService {
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final EventMapper eventMapper;
    private final SeatMapper seatMapper;
    private final ShowMapper showMapper;


    public EventDto getEvent(Long eventId) {
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event %d is not found".formatted(eventId)));
        return eventMapper.toDto(event);
    }

    public List<ShowDto> upcomingShows(Long eventId, String city) {
        eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event %d is not found".formatted(eventId)));
        List<ShowEntity> shows;
        if (city == null || city.isBlank()) {
            shows = showRepository.getUpcomingShows(eventId, Instant.now());
        } else {
            shows = showRepository.getUpcomingShows(eventId, city);
        }
        return shows.stream().map(showMapper::toDto).toList();
    }

    public ShowDto getShow(Long showId) {
        ShowEntity show = showRepository.findById(showId)
                .orElseThrow(() -> new EntityNotFoundException("Show with id %d is not found.".formatted(showId)));
        return showMapper.toDto(show);
    }

    public List<SeatDto> findSeatsByShowId(Long showId) {
        return seatRepository.findSeatsByShowId(showId).stream().map(seatMapper::toDto).toList();
    }
}
