package com.sparta.commerce_project_01.domain.user.mapper;

import com.sparta.commerce_project_01.domain.user.dto.UserCreateRequest;
import com.sparta.commerce_project_01.domain.user.dto.UserResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserSearchResponse;
import com.sparta.commerce_project_01.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponse toResponse(User user);

  UserSearchResponse toSearchResponse(User user);

  //  @Mapping(source = "password", target = "password_hash")
  User toEntity(UserCreateRequest request);
}
