package com.realm.auth_service.user.mapper;

import com.realm.auth_service.role.entity.RoleEntity;
import com.realm.auth_service.role.mapper.RoleMapper;
import com.realm.auth_service.user.dto.UserDto;
import com.realm.auth_service.user.entity.UserEntity;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {RoleMapper.class})
public interface UserMapper {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    @Mapping(target = "currentRole", source = "currentRole", qualifiedByName = "mapRole")
    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    @Mapping(target = "currentRole", source = "currentRole", qualifiedByName = "mapRole")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserDto userDto, @MappingTarget UserEntity userEntity);

    @Named("mapRoles")
    private List<RoleEntity> mapRoles(List<RoleEntity> roles) {
        if (roles == null) return new ArrayList<>();
        return roles.stream().map(this::mapRole).toList();
    }

    @Named("mapRole")
    private RoleEntity mapRole(RoleEntity role) {
        if (role == null) return null;
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        return entity;
    }
}