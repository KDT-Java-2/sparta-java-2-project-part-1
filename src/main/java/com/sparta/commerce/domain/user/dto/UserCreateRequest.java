package com.sparta.commerce.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @NotBlank(message = "이름은 필수입니다")
  String name;

  @NotBlank(message = "이메일은 필수입니다")
  @Email(message = "올바른 이메일 형식이 아닙니다")
  String email;

  @NotBlank(message = "비밀번호는 필수입니다")
  @Pattern(regexp = "^.{8,}$", message = "비밀번호는 최소 8자 이상이어야 합니다.")
  String password;

  String phone;

  String address;


}
