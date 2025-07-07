package com.example.shoppingmall.domain.user.dto;

import com.example.shoppingmall.common.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @NotBlank
    @Size(max = 20)
    String name;

    @Email
    @NotBlank
    @Size(max = 50)
    String email;

    @NotBlank
    @Size(min = 8, max = 100)
    String password;

    @NotBlank
    @Size(max = 20)
    String phone;

    @NotNull
    Gender gender;

    @Past
    LocalDate birthDate;

    @Size(max = 500)
    String profileImageUrl;

    @NotNull
    LocalDateTime termsAgreedAt;

    @NotNull
    LocalDateTime privacyAgreedAt;

    LocalDateTime marketingAgreedAt;
} 