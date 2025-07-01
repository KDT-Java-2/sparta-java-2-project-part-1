package com.sparta.project1.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotNull
    String username;

    @Email
    String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    String password;
}
