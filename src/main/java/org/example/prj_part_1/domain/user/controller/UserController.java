package org.example.prj_part_1.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prj_part_1.common.response.ApiResponse;
import org.example.prj_part_1.domain.user.dto.UserCreateRequest;
import org.example.prj_part_1.domain.user.dto.UserCreateResponse;
import org.example.prj_part_1.domain.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ApiResponse<UserCreateResponse> create(@Valid @RequestBody UserCreateRequest request){
    UserCreateResponse response = userService.create(request);
    return ApiResponse.success(response);
  }
}
