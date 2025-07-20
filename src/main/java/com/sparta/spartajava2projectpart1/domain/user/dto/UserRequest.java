package com.sparta.spartajava2projectpart1.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "Name is required")
    String name;
    @Email(message = "Email should be valid")
    String email;
    @NotBlank(message = "Password is required")
    String password;
    Boolean agree;
    Boolean thirdAgree;
    Boolean marketing;

}
