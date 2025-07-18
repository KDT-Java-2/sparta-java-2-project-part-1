package com.socialcommerce.domain.user.mapper;

import com.socialcommerce.domain.user.dto.UserCreateResponse;
import com.socialcommerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  // 반환이되는 UserCreateResponse 에 username 필드는 User의 name 필드이다.
  @Mapping(target = "username", source = "name")
  UserCreateResponse toCreateResponse(User user);
}
