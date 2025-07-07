package com.sparta.bootcamp.shop.domain.user.mapper;

import com.sparta.bootcamp.shop.domain.user.dto.UserSearchResponse;
import com.sparta.bootcamp.shop.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserSearchResponse toSearch(User user);

  //UserResponse toResponse(User user);
  //User toEntity(UserCreateRequest request);

}