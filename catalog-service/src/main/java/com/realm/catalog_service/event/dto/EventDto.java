package com.realm.catalog_service.event.dto;

import com.realm.catalog_service.utils.AuditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.realm.catalog_service.event.entity.EventEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto extends AuditDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String language;
    private Integer durationMins;
    private String posterUrl;
}