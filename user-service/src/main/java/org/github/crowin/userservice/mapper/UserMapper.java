package org.github.crowin.userservice.mapper;

import org.github.crowin.userservice.dto.NewUserRequest;
import org.github.crowin.userservice.dto.UserResponse;
import org.github.crowin.userservice.repository.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    public abstract UserEntity toEntity(NewUserRequest dto);

    public abstract UserResponse toDto(UserEntity entity);

    @AfterMapping
    protected void hashPassword(NewUserRequest dto, @MappingTarget UserEntity entity) {
        entity.setPasswordHash(passwordEncoder.encode(dto.password()));
    }
}
