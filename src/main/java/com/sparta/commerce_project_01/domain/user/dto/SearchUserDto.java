package com.sparta.commerce_project_01.domain.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchUserDto {

  Long id;
  String name;
  String email;
  String cellPhone;
  String role;
  String status;
  String createdAt;
  String updatedAt;
}
