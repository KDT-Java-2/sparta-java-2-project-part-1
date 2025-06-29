package com.sparta.commerce_project_01.domain.user.controller;

import com.sparta.commerce_project_01.common.response.ApiResponse;
import com.sparta.commerce_project_01.domain.product.service.ProductService;
import com.sparta.commerce_project_01.domain.user.dto.UserCreateRequest;
import com.sparta.commerce_project_01.domain.user.dto.UserResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserSearchResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserUpdateStatusRequest;
import com.sparta.commerce_project_01.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final로 선언된 변수들만 골라서 생성자 만들어줌
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final ProductService productService;

  @PostMapping
  public ApiResponse<UserResponse> save(@Valid @RequestBody UserCreateRequest userRequest) {
    return ApiResponse.success(userService.save(userRequest));
  }

  @GetMapping // GET /api/users?email="abc@abc.com"
  public ApiResponse<List<UserSearchResponse>> findAll() {
    return ApiResponse.success(userService.searchAll());
  }

  @GetMapping("{userId}") // GET /api/users?email="abc@abc.com"
  public ResponseEntity<UserResponse> findById(@PathVariable Long userId) {
    return ResponseEntity.status(200).body(userService.getById(userId));
  }

  @PutMapping("{userId}")
  public ResponseEntity<UserResponse> update(@PathVariable Long userId,
      @Valid @RequestBody UserCreateRequest userRequest) {
    return ResponseEntity.ok(userService.update(userId, userRequest));
  }

  @PatchMapping("{userId}")
  public ResponseEntity<UserResponse> updateStatus(@PathVariable Long userId,
      @Valid @RequestBody UserUpdateStatusRequest userUpdateStatusRequest) {
    return ResponseEntity.ok(userService.updateStatus(userId, userUpdateStatusRequest));
  }

  @DeleteMapping("{userId}")
  public ResponseEntity<Void> delete(@PathVariable Long userId) {
    userService.delete(userId);
    // 204 No Content 상태코드 응답
    return ResponseEntity.noContent().build();
  }
}
