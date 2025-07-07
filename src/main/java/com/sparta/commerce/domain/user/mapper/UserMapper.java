package com.sparta.commerce.domain.user.mapper;

import com.sparta.commerce.domain.user.dto.UserResponse;
import com.sparta.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserResponse toResponse(User user);

}
