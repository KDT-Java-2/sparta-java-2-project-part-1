package com.js.commerce.domain.product.controller;

import com.js.commerce.common.response.ApiResponse;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateRequest;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateResponse;
import com.js.commerce.domain.product.service.AdminProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

  private final AdminProductService adminProductService;

  @PostMapping
  public ApiResponse<AdminProductCreateResponse> createProduct(
      @Valid @RequestBody AdminProductCreateRequest request) {
    return ApiResponse.success(adminProductService.createProduct(request));
  }

}
