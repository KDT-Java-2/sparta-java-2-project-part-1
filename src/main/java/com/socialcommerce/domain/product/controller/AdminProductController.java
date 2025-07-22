package com.socialcommerce.domain.product.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.product.dto.ProductCreateRequest;
import com.socialcommerce.domain.product.dto.ProductIdResponse;
import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.service.AdminProductService;
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

  private final AdminProductService adminProductService;

  // AdminProductController (관리자용)
  @PostMapping
  public ApiResponse<ProductIdResponse> createProduct(@RequestBody @Valid ProductCreateRequest request) {
    ProductIdResponse result = adminProductService.createProduct(request);
    return ApiResponse.success(result);
  }

  @PutMapping("/{productId}")
  public ApiResponse<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductCreateRequest request){
    ProductResponse result = adminProductService.updateProduct(productId, request);
    return ApiResponse.success(result);
  }

  @DeleteMapping
  public ApiResponse<Void> deleteProduct(@PathVariable Long productId){
    adminProductService.deleteProduct(productId);
    return ApiResponse.success();
  }
}
