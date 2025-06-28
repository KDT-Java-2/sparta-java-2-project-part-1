package com.sparta.part_1.domain.user.controller;

import com.sparta.part_1.common.respeonse.ApiResponse;
import com.sparta.part_1.domain.user.dto.request.UserJoinRequest;
import com.sparta.part_1.domain.user.dto.response.UserJoinResponse;
import com.sparta.part_1.domain.user.service.UserService;
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
  public ApiResponse<UserJoinResponse> addUsers(
      @Valid @RequestBody UserJoinRequest userJoinRequestDto) {
    return ApiResponse.success(userService.join(userJoinRequestDto));
  }
}
