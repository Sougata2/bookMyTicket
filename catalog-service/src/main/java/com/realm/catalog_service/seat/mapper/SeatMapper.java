package com.realm.catalog_service.seat.mapper;

import com.realm.catalog_service.seat.dto.SeatDto;
import com.realm.catalog_service.seat.entity.SeatEntity;
import com.realm.catalog_service.show.mapper.ShowMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ShowMapper.class})
public interface SeatMapper {
    SeatEntity toEntity(SeatDto seatDto);

    SeatDto toDto(SeatEntity seatEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SeatEntity partialUpdate(SeatDto seatDto, @MappingTarget SeatEntity seatEntity);
}