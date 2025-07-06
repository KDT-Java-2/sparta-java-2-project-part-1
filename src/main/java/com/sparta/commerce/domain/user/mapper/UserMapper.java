package com.sparta.commerce.domain.user.mapper;

import com.sparta.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.commerce.domain.user.dto.UserResponse;
import com.sparta.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "passwordHash", source = "encodePassword")
  User toEntity(UserCreateRequest request, String encodePassword);

  UserResponse toResponse(User user);

}
