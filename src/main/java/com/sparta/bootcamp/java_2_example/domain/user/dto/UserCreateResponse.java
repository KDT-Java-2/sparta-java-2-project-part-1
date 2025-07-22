package com.sparta.bootcamp.java_2_example.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
