package com.sparta.project1.domain.user.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.user.dto.UserRequest;
import com.sparta.project1.domain.user.dto.UserResponse;
import com.sparta.project1.domain.user.entity.User;
import com.sparta.project1.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ApiResponse<UserResponse> create(@Valid @RequestBody UserRequest request) {
    return ApiResponse.success(userService.create(request));
  }
}