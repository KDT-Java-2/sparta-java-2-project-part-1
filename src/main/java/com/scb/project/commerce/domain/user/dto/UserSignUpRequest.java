package com.scb.project.commerce.domain.user.dto;

import com.scb.project.commerce.common.enums.UserRole;
import com.scb.project.commerce.common.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpRequest {

    // 사용자 이름
    @NotBlank   // 빈문자열 막기 위함
        String name;

    // 이메일
    @NotBlank   // 빈문자열 막기 위함
    @Email
    String email;

    // 비밀번호
    @NotBlank   // 빈문자열 막기 위함
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,}$",
        message = "비밀번호는 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 각각 하나 이상 포함해야 합니다."
    )
    String password;

    // 기본값 설정
    final UserRole role = UserRole.CUSTOMER;
    final UserStatus status = UserStatus.ACTIVE;
}
