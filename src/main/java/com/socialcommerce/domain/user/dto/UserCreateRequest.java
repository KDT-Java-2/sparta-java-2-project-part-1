package com.socialcommerce.domain.user.dto;

import com.socialcommerce.common.enums.Gender;
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
  String nickName;
  String name;
  String email;
  LocalDate dateOfBirth;
  Gender gender;
  String phoneNumber;
  String password;
}
