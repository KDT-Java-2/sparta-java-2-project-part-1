package com.scb.masterCourse.commerce.domain.user.mapper;

import com.scb.masterCourse.commerce.domain.user.dto.UserCreateRequest;
import com.scb.masterCourse.commerce.domain.user.dto.UserCreateResponse;
import com.scb.masterCourse.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "username")
    User toEntity(UserCreateRequest request);

    @Mapping(target = "username", source = "name")
    UserCreateResponse toResponse(User savedUser);
}
