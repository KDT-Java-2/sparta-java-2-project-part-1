package com.js.commerce.domain.user.controller;

import com.js.commerce.common.response.ApiResponse;
import com.js.commerce.domain.user.dto.UserCreateRequest;
import com.js.commerce.domain.user.dto.UserResponse;
import com.js.commerce.domain.user.service.UserService;
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
  public ApiResponse<UserResponse> create(
      @Valid @RequestBody UserCreateRequest request) {
    UserResponse response = userService.register(request);
    return ApiResponse.success(response);
  }

}
