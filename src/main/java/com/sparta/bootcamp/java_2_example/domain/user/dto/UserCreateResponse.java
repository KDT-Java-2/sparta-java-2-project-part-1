package com.sparta.bootcamp.java_2_example.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateResponse {

  Long id;

  String email;

  String username;

  public UserCreateResponse(
      Long id,
      String email,
      String username
  ) {
    this.id = id;
    this.email = email;
    this.username = username;
  }
}
