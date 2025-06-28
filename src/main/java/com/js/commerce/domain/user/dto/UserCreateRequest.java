package com.js.commerce.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @Email
  @NotBlank
  String email;

  @NotBlank
  @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
  String password;

  @NotBlank
  String username;
}
