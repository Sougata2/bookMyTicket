package com.realm.catalog_service.show.dto;

import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.screen.dto.ScreenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.realm.catalog_service.show.entity.ShowEntity}
 */
@AllArgsConstructor
@Getter
public class ShowDto implements Serializable {
    private final Long id;
    private final EventDto event;
    private final ScreenDto screen;
    private final Instant startTime;
    private final BigDecimal basePrice;
}