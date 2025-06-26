package com.sparta.java2.project.part1.commerce.domain.user.mapper;

import com.sparta.java2.project.part1.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserResponse;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserUpdateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserSearchResponse toSearchResponse(User user);
    UserResponse toUserResponse(User user);
    User toUser(UserCreateRequest userCreateRequest);
    User toUser(UserUpdateRequest userUpdateRequest);
}
