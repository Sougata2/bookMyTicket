package com.realm.auth_service.auth.mapper;

import com.realm.auth_service.auth.dto.RefreshTokenDto;
import com.realm.auth_service.auth.entity.RefreshTokenEntity;
import com.realm.auth_service.user.dto.UserDto;
import com.realm.auth_service.user.entity.UserEntity;
import com.realm.auth_service.user.mapper.UserMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface RefreshTokenMapper {
    @Mapping(source = "user", target = "user", qualifiedByName = {"mapUser"})
    RefreshTokenEntity toEntity(RefreshTokenDto refreshTokenDto);

    RefreshTokenDto toDto(RefreshTokenEntity refreshTokenEntity);

    @Mapping(source = "user", target = "user", qualifiedByName = {"mapUser"})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RefreshTokenEntity partialUpdate(RefreshTokenDto refreshTokenDto, @MappingTarget RefreshTokenEntity refreshTokenEntity);

    @Named("mapUser")
    default UserEntity mapUser(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        return user;
    }
}