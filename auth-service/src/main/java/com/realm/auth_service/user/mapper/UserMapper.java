package com.realm.auth_service.user.mapper;

import com.realm.auth_service.user.dto.UserDto;
import com.realm.auth_service.user.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserDto userDto, @MappingTarget UserEntity userEntity);
}