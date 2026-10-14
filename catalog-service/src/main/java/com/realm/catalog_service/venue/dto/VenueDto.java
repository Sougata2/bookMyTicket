package com.realm.catalog_service.venue.dto;

import com.realm.catalog_service.utils.AuditDto;
import com.realm.catalog_service.venue.entity.VenueEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link VenueEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VenueDto extends AuditDto implements Serializable {
    private Long id;
    private String name;
    private String city;
    private String address;
}