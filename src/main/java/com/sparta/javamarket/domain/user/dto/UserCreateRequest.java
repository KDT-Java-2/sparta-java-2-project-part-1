package com.sparta.javamarket.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @NotNull
  String name;

  @Email
  String email;

  @Pattern(regexp = "^\\d{8}$", message = "password must be 8 digits")
  String password;

  @NotNull(message = "nickname이 입력되지 않았습니다.")
  String nickname;

  String address;

  String phone;

}
