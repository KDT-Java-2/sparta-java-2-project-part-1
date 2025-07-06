package com.sparta.commerce_project_01.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserCreateRequest {

  @NotNull
  @Size(min = 2, max = 20)
  String name;

  @Email
  String email;

  String password;

  @Pattern(regexp = "\\d{11}$", message = "Phone number must be 11 digit")
  String cellPhone;

  @Min(value = 18, message = "Age must be greater than 18")
  Integer age;

  boolean isAcceptTerms;
  boolean isAcceptPrivacy;
  boolean isAcceptMarketing;
}
