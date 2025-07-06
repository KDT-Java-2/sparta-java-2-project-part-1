package com.sparta.commerce_project_01.domain.user.dto;

import com.sparta.commerce_project_01.common.enums.UserRole;
import com.sparta.commerce_project_01.common.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserResponse {

  Long id;
  String name;
  String email;
  UserRole role;
  UserStatus status;
  String cellPhone;
  String acceptTermsAt;
  String acceptPrivacyAt;
  String acceptMarketingAt;
  String lastLogin;
  String createdAt;
}
