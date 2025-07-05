package com.sparta.javamarket.domain.admin.controller;

import com.sparta.javamarket.common.response.ApiResponse;
import com.sparta.javamarket.domain.admin.dto.AdminCreateRequest;
import com.sparta.javamarket.domain.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final AdminService adminService;

  @PostMapping("/products")
  public ApiResponse<?> addProducts(@Valid @RequestBody AdminCreateRequest adminCreateRequest) {
    return ApiResponse.success(adminService.adminCreateProduct(adminCreateRequest));
  }
}
