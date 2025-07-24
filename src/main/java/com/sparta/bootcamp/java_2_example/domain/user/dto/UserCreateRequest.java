package com.sparta.bootcamp.java_2_example.domain.user.dto;

import com.sparta.bootcamp.java_2_example.common.exception.ServiceExceptionCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

  @NotBlank
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  String email;

  @Setter
  @NotBlank
  @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
  String password;

  @NotBlank
  String username;

}
