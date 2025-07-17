package com.scb.masterCourse.commerce.domain.user.controller;

import com.scb.masterCourse.commerce.common.response.ApiResponse;
import com.scb.masterCourse.commerce.domain.user.dto.UserCreateRequest;
import com.scb.masterCourse.commerce.domain.user.dto.UserCreateResponse;
import com.scb.masterCourse.commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping
    public ApiResponse<UserCreateResponse> signup(@Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.success(userService.signup(request));
    }
}
