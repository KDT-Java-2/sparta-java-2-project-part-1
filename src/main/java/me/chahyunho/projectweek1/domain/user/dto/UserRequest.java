package me.chahyunho.projectweek1.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

  @NotNull
  String name;

  @Email
  String email;

  @NotNull
  @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
  @Setter
  String passwordHash;
}
