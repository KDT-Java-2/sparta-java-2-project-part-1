package com.sparta.commerce_project_01.domain.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserSearchResponse {

  Long id;
  String name;
  String email;
  String role;
  String cellPhone;
  String acceptTermsAt;
  String acceptPrivacyAt;
  String acceptMarketingAt;
  String createdAt;
  String updatedAt;
  String lastLogin;
}
