package com.sparta.java2.project.part1.commerce.domain.user.mapper;

import com.sparta.java2.project.part1.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserResponse;
import com.sparta.java2.project.part1.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "username")
    User toUser(UserCreateRequest userCreateRequest);

    @Mapping(target = "username", source = "name")
    UserResponse toUserResponse(User user);
}
