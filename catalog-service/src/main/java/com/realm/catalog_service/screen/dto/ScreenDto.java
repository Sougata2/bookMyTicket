package com.realm.catalog_service.screen.dto;

import com.realm.catalog_service.utils.AuditDto;
import com.realm.catalog_service.venue.dto.VenueDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.realm.catalog_service.screen.entity.ScreenEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDto extends AuditDto implements Serializable {
    private Long id;
    private VenueDto venue;
    private String name;
    private Integer totalRows;
    private Integer seatsPerRow;
}