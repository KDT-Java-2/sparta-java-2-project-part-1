package com.sparta.coupang_commerce.domain.user.mapper;

import com.sparta.coupang_commerce.domain.user.dto.UserCreateRequest;
import com.sparta.coupang_commerce.domain.user.dto.UserResponse;
import com.sparta.coupang_commerce.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


  UserResponse toResponse(User user);

  User toCreateEntity(UserCreateRequest request, String passwordHash, String phoneNumHash);
}
