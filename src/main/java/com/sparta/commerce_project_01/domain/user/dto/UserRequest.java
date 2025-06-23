package com.sparta.commerce_project_01.domain.user.dto;

import com.sparta.commerce_project_01.common.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserRequest {

  String name;
  String email;
  String password_hash;
  UserRole role;
  String cellPhone;
  String acceptTermsAt;
  String acceptPrivacyAt;
  String acceptMarketingAt;
}
