package com.sparta.java_02.domain.user.controller;

import com.sparta.java_02.common.dto.ApiResponse;
import com.sparta.java_02.domain.user.dto.UserResponseDto;
import com.sparta.java_02.domain.user.dto.UserSignUpRequestDto;
import com.sparta.java_02.domain.user.dto.UserUpdateRequestDto;
import com.sparta.java_02.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // [POST] 회원 등록 API 엔드포인트
  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<UserResponseDto>> signUp(
      @Valid @RequestBody UserSignUpRequestDto requestDto) {

    UserResponseDto responseDto = new UserResponseDto(
        1L,
        requestDto.getName(),
        requestDto.getEmail(),
        LocalDateTime.now(),
        LocalDateTime.now()
    );
    ApiResponse<UserResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED, responseDto);
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable Long userId) {

    UserResponseDto responseDto = new UserResponseDto(
        userId,
        "TestUser",
        "testuser@example.com",
        LocalDateTime.now(),
        LocalDateTime.now()
    );

    ApiResponse<UserResponseDto> apiResponse = ApiResponse.success(responseDto);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
      @PathVariable Long userId,
      @Valid @RequestBody UserUpdateRequestDto requestDto) {

    // Service 계층이 없으므로, 가상의 응답 DTO를 생성합니다.
    UserResponseDto responseDto = new UserResponseDto(
        userId,
        requestDto.getName(),
        requestDto.getEmail(),
        LocalDateTime.now().minusHours(1),
        LocalDateTime.now()
    );

    ApiResponse<UserResponseDto> apiResponse = ApiResponse.success(responseDto);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

}
