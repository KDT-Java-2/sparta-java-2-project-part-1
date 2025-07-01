package com.sparta.project1.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;

    String name;

    String email;

    String password;

    LocalDateTime createdAt;
}
