package com.realm.catalog_service.show.mapper;

import com.realm.catalog_service.event.mapper.EventMapper;
import com.realm.catalog_service.screen.mapper.ScreenMapper;
import com.realm.catalog_service.show.dto.ShowDto;
import com.realm.catalog_service.show.entity.ShowEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {EventMapper.class, ScreenMapper.class})
public interface ShowMapper {
    ShowEntity toEntity(ShowDto showDto);

    ShowDto toDto(ShowEntity showEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ShowEntity partialUpdate(ShowDto showDto, @MappingTarget ShowEntity showEntity);
}