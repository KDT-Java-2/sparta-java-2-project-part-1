package com.sparta.commerce.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
  private Long id;
  private String email;
  private String nickname;
}
