package com.realm.catalog_service.screen.mapper;

import com.realm.catalog_service.screen.dto.ScreenDto;
import com.realm.catalog_service.screen.entity.ScreenEntity;
import com.realm.catalog_service.venue.mapper.VenueMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {VenueMapper.class})
public interface ScreenMapper {
    ScreenEntity toEntity(ScreenDto screenDto);

    ScreenDto toDto(ScreenEntity screenEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScreenEntity partialUpdate(ScreenDto screenDto, @MappingTarget ScreenEntity screenEntity);
}