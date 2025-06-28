package com.sparta.java2.project.part1.commerce.domain.user.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserResponse;
import com.sparta.java2.project.part1.commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> createUser(
            @Valid @RequestBody UserCreateRequest userCreateRequest
    ) {
        return ApiResponse.success(userService.createUser(userCreateRequest));
    }
}
