package me.chahyunho.projectweek1.domain.user.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.response.ApiResponse;
import me.chahyunho.projectweek1.domain.user.dto.UserRequest;
import me.chahyunho.projectweek1.domain.user.dto.UserResponse;
import me.chahyunho.projectweek1.domain.user.service.UserService;
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
  public ApiResponse<UserResponse> create(@Valid @RequestBody
  UserRequest request) {
    return ApiResponse.success(userService.create(request));
  }
}
