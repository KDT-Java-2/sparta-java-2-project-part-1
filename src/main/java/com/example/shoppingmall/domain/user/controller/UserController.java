package com.example.shoppingmall.domain.user.controller;

import com.example.shoppingmall.common.response.ApiResponse;
import com.example.shoppingmall.domain.user.dto.UserCreateRequest;
import com.example.shoppingmall.domain.user.dto.UserResponse;
import com.example.shoppingmall.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 성공 시 201 Created 상태 코드 반환
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.success(userService.createUser(request));
    }
}