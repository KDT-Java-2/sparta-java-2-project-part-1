package com.sparta.part_1.domain.user.mapper;

import com.sparta.part_1.domain.user.dto.request.UserJoinRequestDto;
import com.sparta.part_1.domain.user.dto.response.UserJoinResponse;
import com.sparta.part_1.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

  User toUserEntity(UserJoinRequestDto dto);

  UserJoinResponse toUserJoinResponse(User user);
}