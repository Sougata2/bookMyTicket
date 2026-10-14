package com.realm.catalog_service.venue.mapper;

import com.realm.catalog_service.venue.dto.VenueDto;
import com.realm.catalog_service.venue.entity.VenueEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VenueMapper {
    VenueEntity toEntity(VenueDto venueDto);

    VenueDto toDto(VenueEntity venueEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VenueEntity partialUpdate(VenueDto venueDto, @MappingTarget VenueEntity venueEntity);
}