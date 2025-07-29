package com.sparta.commerce_project_01.domain.user.repository;

import com.sparta.commerce_project_01.domain.user.dto.SearchUserDto;
import com.sparta.commerce_project_01.domain.user.dto.SearchUserRequestWithPagination;
import com.sparta.commerce_project_01.domain.user.dto.UserDto;
import com.sparta.commerce_project_01.domain.user.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapperRepository {

  SearchUserDto getUsersById(Long id);

  void insertUser(User user);

  void insertUser(UserDto user);

  void insertUsers(@Param("users") List<UserDto> users);

  void updateUser(UserDto user);

  void deleteUser(Long id);

  List<SearchUserDto> getUserWithPagination(SearchUserRequestWithPagination request);

}

