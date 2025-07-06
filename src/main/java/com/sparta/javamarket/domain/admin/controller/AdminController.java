package com.sparta.javamarket.domain.admin.controller;

import com.sparta.javamarket.common.response.ApiResponse;
import com.sparta.javamarket.domain.admin.dto.AdminCreateRequest;
import com.sparta.javamarket.domain.admin.service.AdminService;
import com.sparta.javamarket.domain.product.dto.ProductSearchResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminController {

  private final AdminService adminService;

  @PostMapping
  public ApiResponse<?> createAdminProducts(@Valid @RequestBody AdminCreateRequest adminCreateRequest) {
    return ApiResponse.success(adminService.adminCreateProduct(adminCreateRequest));
  }

  @PutMapping("/{productId}")
  public ApiResponse<ProductSearchResponse> updateAdminProduct(@PathVariable Long productId, @Valid @RequestBody AdminCreateRequest adminCreateRequest) {
    return ApiResponse.success(adminService.adminUpdateProduct(productId, adminCreateRequest));
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<?> deleteAdminProducts(@PathVariable Long productId) {
    adminService.adminDeleteProduct(productId);
    return ApiResponse.success();
  }
}
