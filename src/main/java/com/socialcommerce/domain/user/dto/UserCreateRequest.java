package com.socialcommerce.domain.user.dto;

import com.socialcommerce.common.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
  @NotBlank
  String nickName;
  @NotBlank
  String name;
  @NotBlank
  String email;
  @NotBlank
  LocalDate dateOfBirth;
  @NotBlank
  Gender gender;
  @NotBlank
  String phoneNumberHash;
  @NotBlank
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$", message = "비밀번호 형식이 아닙니다.")
  String passwordHash;
}
