package com.sparta.java2.project.part1.commerce.domain.user.dto;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String id;

    @Setter
    String username;

    String email;
}
