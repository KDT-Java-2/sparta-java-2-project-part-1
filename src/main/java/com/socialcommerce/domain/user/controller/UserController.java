package com.socialcommerce.domain.user.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.user.dto.UserCreateRequest;
import com.socialcommerce.domain.user.dto.UserCreateResponse;
import com.socialcommerce.domain.user.service.UserService;
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
  public ApiResponse<UserCreateResponse> create(@RequestBody @Valid UserCreateRequest request){
    userService.create(request);
    return ApiResponse.success();
  }
}
