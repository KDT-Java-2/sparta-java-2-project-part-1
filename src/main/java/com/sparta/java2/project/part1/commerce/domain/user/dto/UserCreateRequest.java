package com.sparta.java2.project.part1.commerce.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;

    @NotBlank
    String username;
}
