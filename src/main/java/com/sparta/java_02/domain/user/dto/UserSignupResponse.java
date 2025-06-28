package com.sparta.java_02.domain.user.dto;

import com.sparta.java_02.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupResponse {
    private Long id;
    private String email;
    private String username;

    public static UserSignupResponse from(User user) {
        return new UserSignupResponse(
            user.getId(),
            user.getEmail(),
            user.getUsername()
        );
    }
} 