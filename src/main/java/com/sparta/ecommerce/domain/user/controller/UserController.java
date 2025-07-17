package com.sparta.ecommerce.domain.user.controller;

import com.sparta.ecommerce.common.response.ApiResponse;
import com.sparta.ecommerce.domain.user.dto.UserCreateRequest;
import com.sparta.ecommerce.domain.user.dto.UserCreateResponse;
import com.sparta.ecommerce.domain.user.service.UserService;
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

  @PostMapping
  public ApiResponse<UserCreateResponse> create(@Valid @RequestBody UserCreateRequest request) {
    return ApiResponse.success(userService.create(request));
  }
}
