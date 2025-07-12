package com.sparta.commerce.domain.user.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.user.entity.UserCreateRequest;
import com.sparta.commerce.domain.user.entity.UserCreateResponse;
import com.sparta.commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // View를 위한 컨트롤러가 아닌 RestController를 위한 어노테이션
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  @PostMapping("/signup")
  public ApiResponse<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest createRequest) {
    // POST /api/users/signup
    UserCreateResponse response = userService.createUser(createRequest);
    return ApiResponse.success(response);
  }
}
