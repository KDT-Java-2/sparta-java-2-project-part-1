package com.sparta.coupang_commerce.domain.user.controller;

import com.sparta.coupang_commerce.common.response.ApiResponse;
import com.sparta.coupang_commerce.domain.user.dto.UserCreateRequest;
import com.sparta.coupang_commerce.domain.user.dto.UserResponse;
import com.sparta.coupang_commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userRequest) {
    return ApiResponse.success(userService.createUser(userRequest));
  }

}
