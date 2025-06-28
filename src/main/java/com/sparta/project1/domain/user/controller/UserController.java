package com.sparta.project1.domain.user.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.user.dto.UserResponse;
import com.sparta.project1.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/products")
    public ApiResponse<List<UserResponse>> findAll() {
        return ApiResponse.success(userService.searchUser());
    }
}
