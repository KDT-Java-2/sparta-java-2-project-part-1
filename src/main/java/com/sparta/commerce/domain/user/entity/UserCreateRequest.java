package com.sparta.commerce.domain.user.entity;

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
  String username;

  @Email
  @NotNull
  String email;

  @NotNull
  String phoneNumber;

  @NotNull
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
      message = "Password must be at least 8 characters long and contain both letters and numbers.")
  String password;
}
