package com.sparta.commerce_project_01.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserResponse {

  Long id;
  String name;
  String email;
  String cellPhone;
  LocalDateTime createdAt;

  @QueryProjection
  public UserResponse(
      Long id,
      String name,
      String email,
      String cellPhone,
      LocalDateTime createdAt
  ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.cellPhone = cellPhone;
    this.createdAt = createdAt;
  }
}
