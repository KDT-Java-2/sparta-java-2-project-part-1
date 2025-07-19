package com.sparta.ecommerce.domain.admin.controller;

import com.sparta.ecommerce.common.response.ApiResponse;
import com.sparta.ecommerce.domain.product.dto.ProductCreateRequest;
import com.sparta.ecommerce.domain.product.dto.ProductCreateResponse;
import com.sparta.ecommerce.domain.product.dto.ProductInfoResponse;
import com.sparta.ecommerce.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  private final ProductService productService;

  @PostMapping
  public ApiResponse<ProductCreateResponse> setProductInfo(
      @Valid @RequestBody ProductCreateRequest request) {
    return ApiResponse.success(productService.save(request));
  }

  @PutMapping("/{productId}")
  public ApiResponse<ProductInfoResponse> putProductInfo(@PathVariable Long productId,
      @Valid @RequestBody ProductCreateRequest request) {
    return ApiResponse.success(productService.update(productId, request));
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<Void> delProductInfo(@PathVariable Long productId) {
    productService.delete(productId);

    return ApiResponse.success();
  }
}