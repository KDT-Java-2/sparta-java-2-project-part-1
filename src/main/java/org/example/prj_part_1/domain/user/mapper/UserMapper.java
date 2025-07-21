package org.example.prj_part_1.domain.user.mapper;

import org.example.prj_part_1.domain.user.dto.UserCreateRequest;
import org.example.prj_part_1.domain.user.dto.UserCreateResponse;
import org.example.prj_part_1.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "passwordHash", source = "hashedPassword")
  User createRequestToEntity(UserCreateRequest request, String hashedPassword);
  UserCreateResponse toCreateResponse(User user);

}
