package com.java_project.part_1.domain.user.controller;

import com.java_project.part_1.common.response.ApiResponse;
import com.java_project.part_1.domain.user.dto.UserCreateRequest;
import com.java_project.part_1.domain.user.entity.User;
import com.java_project.part_1.domain.user.service.UserService;
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
  public ApiResponse<User> createUser(@Valid @RequestBody UserCreateRequest request) {
    userService.save(request);
    return ApiResponse.success();
  }
}
