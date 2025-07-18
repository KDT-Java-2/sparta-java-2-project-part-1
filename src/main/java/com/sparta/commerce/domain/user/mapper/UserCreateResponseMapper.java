package com.sparta.commerce.domain.user.mapper;

import com.sparta.commerce.domain.user.entity.User;
import com.sparta.commerce.domain.user.entity.UserCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreateResponseMapper {
  @Mapping(source = "name", target = "username") // User의 name -> UserCreateResponse의 username으로 매핑
  UserCreateResponse toResponse(User user);
}
