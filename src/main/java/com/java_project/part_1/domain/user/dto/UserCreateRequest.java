package com.java_project.part_1.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @NotNull
  String name;

  @NotBlank(message = "Email must be not null.")
  String email;

  @NotNull
  String password;
}
