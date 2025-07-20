package com.sparta.project1.domain.user.mapper;

import com.sparta.project1.domain.user.dto.UserResponse;
import com.sparta.project1.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}