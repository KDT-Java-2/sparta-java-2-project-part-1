package com.sparta.project1.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

  @Email(message = "이메일 주소가 유효하지 않습니다.")
  @NotNull(message = "이메일은 필수입력입니다.")
  String email;

  @NotNull(message = "이름은 필수입력입니다.")
  String username;

  @Size(min = 8, message = "비밀번호는 최소 8자리입니다.")
  @NotNull(message = "비밀번호는 필수입력입니다.")
  String password;
}
