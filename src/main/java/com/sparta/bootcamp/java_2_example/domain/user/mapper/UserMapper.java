package com.sparta.bootcamp.java_2_example.domain.user.mapper;

import com.sparta.bootcamp.java_2_example.domain.user.dto.UserCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.user.dto.UserCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "password", target = "passwordHash")
    @Mapping(source = "username", target = "name")
    User toEntity(UserCreateRequest Request);

    @Mapping(source = "name", target = "username")
    UserCreateResponse toCreateResponse(User user);

}
