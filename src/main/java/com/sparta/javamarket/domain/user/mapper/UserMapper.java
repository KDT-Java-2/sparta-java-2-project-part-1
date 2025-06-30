package com.sparta.javamarket.domain.user.mapper;

import com.sparta.javamarket.domain.user.entity.User;
import com.sparta.javamarket.domain.user.dto.UserSearchResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserSearchResponse toSearch(User user) ;
  UserSearchResponse toResponse(User user);

}
