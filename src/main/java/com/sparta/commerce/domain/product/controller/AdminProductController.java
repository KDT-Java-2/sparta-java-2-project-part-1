package com.sparta.commerce.domain.product.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.product.dto.ProductCreateRequest;
import com.sparta.commerce.domain.product.dto.ProductCreateResponse;
import com.sparta.commerce.domain.product.dto.ProductUpdateResponse;
import com.sparta.commerce.domain.product.service.AdminProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

  private final AdminProductService adminProductService;

  @PostMapping()
  public ApiResponse<ProductCreateResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
    return ApiResponse.success(adminProductService.createProduct(request));

  }

  @PutMapping("/{productId}")
  public ApiResponse<ProductUpdateResponse> updateProduct(@Valid @RequestBody ProductCreateRequest request,
      @PathVariable Long productId) {

    return ApiResponse.success(adminProductService.updateProduct(request, productId));
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<Void> deleteProduct(@PathVariable Long productId) {
    adminProductService.deleteProduct(productId);
    return ApiResponse.success();
  }
}
