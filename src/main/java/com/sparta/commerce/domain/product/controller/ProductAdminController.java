package com.sparta.commerce.domain.product.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductCreateRequest;
import com.sparta.commerce.domain.product.dto.admin.ProductResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductUpdateRequest;
import com.sparta.commerce.domain.product.service.ProductService;
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
public class ProductAdminController {

  private final ProductService productService;

  @PostMapping
  public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
    ProductResponse response = productService.createProduct(request);
    return ApiResponse.success(response);
  }


  @PutMapping("/{productId}")
  public ApiResponse<ProductResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductUpdateRequest request) {
    ProductResponse response = productService.updateProduct(productId, request);
    return ApiResponse.success(response);
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<Void> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return ApiResponse.success();
  }

}
