package com.example.shoppingmall.domain.user.dto;

import com.example.shoppingmall.common.enums.Gender;
import com.example.shoppingmall.common.enums.LoginType;
import com.example.shoppingmall.common.enums.UserRole;
import com.example.shoppingmall.common.enums.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    UUID uuid;
    String customerNumber;
    String name;
    String email;
    String phone;
    Gender gender;
    LocalDate birthDate;
    String profileImageUrl;
    LoginType loginType;
    UserStatus status;
    UserRole role;
    Boolean isEmailVerified;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}