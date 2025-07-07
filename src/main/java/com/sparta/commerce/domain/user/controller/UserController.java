package com.sparta.commerce.domain.user.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.user.dto.UserRequest;
import com.sparta.commerce.domain.user.dto.UserResponse;
import com.sparta.commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ApiResponse<UserResponse> create(@Valid @RequestBody UserRequest request){
    UserResponse userResponse = userService.create(request);
    return ApiResponse.success(userResponse);
  }
}
