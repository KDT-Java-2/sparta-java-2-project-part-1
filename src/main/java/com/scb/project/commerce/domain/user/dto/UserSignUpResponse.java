package com.scb.project.commerce.domain.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpResponse {

    // 사용자 아이디
    Long id;

    // 사용자 이름
    String username;

    // 사용자 이메일
    String email;
}
