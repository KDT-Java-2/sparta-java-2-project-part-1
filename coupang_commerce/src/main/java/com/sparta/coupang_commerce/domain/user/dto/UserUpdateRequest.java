package com.sparta.coupang_commerce.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

  @NotNull
  @Size(min = 2, max = 20, message = "Name must be between 2and 20 characters")
  String name;

  @NotNull
  @Email(message = "Email should be valid")
  String email;

  @NotNull
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
      message = "비밀번호는 8~20자, 영문 대소문자·숫자·특수문자를 모두 포함해야 합니다.")
  String password;
}
