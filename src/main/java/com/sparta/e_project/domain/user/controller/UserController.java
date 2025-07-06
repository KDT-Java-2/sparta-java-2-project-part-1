package com.sparta.e_project.domain.user.controller;

import com.sparta.e_project.common.response.ApiResponse;
import com.sparta.e_project.domain.user.dto.UserRequest;
import com.sparta.e_project.domain.user.dto.UserResponse;
import com.sparta.e_project.domain.user.entity.User;
import com.sparta.e_project.domain.user.service.UserService;
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
  public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
    User user = userService.createUser(userRequest);
    return ApiResponse.success(UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .build());
  }
}
