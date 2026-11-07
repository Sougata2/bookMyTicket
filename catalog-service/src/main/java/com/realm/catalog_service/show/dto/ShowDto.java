package com.realm.catalog_service.show.dto;

import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.screen.dto.ScreenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.realm.catalog_service.show.entity.ShowEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto implements Serializable {
    private Long id;
    private EventDto event;
    private ScreenDto screen;
    private Instant startTime;
    private BigDecimal basePrice;
}