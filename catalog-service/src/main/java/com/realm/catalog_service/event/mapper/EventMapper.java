package com.realm.catalog_service.event.mapper;

import com.realm.catalog_service.event.dto.EventDto;
import com.realm.catalog_service.event.entity.EventEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {
    EventEntity toEntity(EventDto eventDto);

    EventDto toDto(EventEntity eventEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventEntity partialUpdate(EventDto eventDto, @MappingTarget EventEntity eventEntity);
}