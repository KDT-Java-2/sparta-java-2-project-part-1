package com.socialcommerce.domain.user.mapper;

import com.socialcommerce.domain.user.dto.UserCreateResponse;
import com.socialcommerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "username", source = "name")
  UserCreateResponse toCreateResponse(User user);
}
