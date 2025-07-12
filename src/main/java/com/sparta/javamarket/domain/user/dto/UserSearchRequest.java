package com.sparta.javamarket.domain.user.dto;

import com.sparta.javamarket.common.enums.UserRoleStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSearchRequest {

  String email;

  String name;

}
