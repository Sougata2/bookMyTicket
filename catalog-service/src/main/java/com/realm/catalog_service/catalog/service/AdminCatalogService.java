package com.realm.catalog_service.catalog.service;

import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.event.entity.EventEntity;
import com.realm.catalog_service.event.mapper.EventMapper;
import com.realm.catalog_service.event.repository.EventRepository;
import com.realm.catalog_service.screen.dto.ScreenDto;
import com.realm.catalog_service.screen.entity.ScreenEntity;
import com.realm.catalog_service.screen.mapper.ScreenMapper;
import com.realm.catalog_service.screen.repository.ScreenRepository;
import com.realm.catalog_service.seat.entity.SeatEntity;
import com.realm.catalog_service.seat.enums.SeatStatus;
import com.realm.catalog_service.seat.repository.SeatRepository;
import com.realm.catalog_service.show.dto.ShowDto;
import com.realm.catalog_service.show.entity.ShowEntity;
import com.realm.catalog_service.show.mapper.ShowMapper;
import com.realm.catalog_service.show.repository.ShowRepository;
import com.realm.catalog_service.venue.dto.VenueDto;
import com.realm.catalog_service.venue.entity.VenueEntity;
import com.realm.catalog_service.venue.mapper.VenueMapper;
import com.realm.catalog_service.venue.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCatalogService {
    private final VenueMapper mapper;
    private final ShowMapper showMapper;
    private final EventMapper eventMapper;
    private final ScreenMapper screenMapper;
    private final VenueRepository repository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;
    private final ScreenRepository screenRepository;

    @Transactional
    public VenueDto createVenue(VenueDto dto) {
        VenueEntity entity = mapper.toEntity(dto);
        entity.setCity(dto.getCity().trim());
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public ScreenDto createScreen(ScreenDto dto) {
        VenueEntity venue = repository.findById(dto.getVenue().getId()).orElseThrow(() -> new EntityNotFoundException("Venue with id %d is not found!"));
        ScreenEntity entity = screenMapper.toEntity(dto);
        entity.setVenue(venue);
        return screenMapper.toDto(screenRepository.save(entity));
    }

    @Transactional
    public EventDto createEvent(EventDto dto) {
        EventEntity entity = eventMapper.toEntity(dto);
        return eventMapper.toDto(eventRepository.save(entity));
    }

    @Transactional
    public ShowDto createShow(ShowDto dto) {
        EventEntity event = eventRepository.findById(dto.getEvent().getId()).orElseThrow(() -> new EntityNotFoundException("Event with id %d is not found"));
        ScreenEntity screen = screenRepository.findById(dto.getScreen().getId()).orElseThrow(() -> new EntityNotFoundException("Screen with id %d is not found"));

        ShowEntity show = showMapper.toEntity(dto);
        show.setScreen(screen);
        show.setEvent(event);

        ShowEntity savedShow = showRepository.save(show);

        List<SeatEntity> seats = new ArrayList<>(screen.getTotalRows() * screen.getSeatsPerRow());
        for (int r = 0; r < screen.getTotalRows(); r++) {
            String rowLabel = String.valueOf((char) ('A' + r));
            for (int n = 1; n < screen.getSeatsPerRow(); n++) {
                SeatEntity seat = new SeatEntity();
                seat.setShow(savedShow);
                seat.setRowLabel(rowLabel);
                seat.setSeatNumber(n);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setPrice(savedShow.getBasePrice());
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
        return showMapper.toDto(savedShow);
    }
}
