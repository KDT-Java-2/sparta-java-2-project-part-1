package me.chahyunho.projectweek1.domain.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

  Long id;

  String email;

  String name;

  @Builder
  public UserResponse(Long id, String email, String name) {
    this.id = id;
    this.email = email;
    this.name = name;
  }
}
