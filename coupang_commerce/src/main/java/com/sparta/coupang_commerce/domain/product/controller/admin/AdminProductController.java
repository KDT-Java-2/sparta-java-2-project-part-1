package com.sparta.coupang_commerce.domain.product.controller.admin;

import com.sparta.coupang_commerce.common.response.ApiResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductResponse;
import com.sparta.coupang_commerce.domain.product.dto.admin.ProductCreateOrUpdateRequest;
import com.sparta.coupang_commerce.domain.product.dto.admin.ProductCreateResponse;
import com.sparta.coupang_commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

  private final ProductService productService;

  @PostMapping
  public ApiResponse<ProductCreateResponse> productRegistration(@RequestBody ProductCreateOrUpdateRequest request) {
    return ApiResponse.success(productService.registerProduct(request));
  }

  @PutMapping("/{productId}")
  public ApiResponse<ProductResponse> productUpdate(@PathVariable Long productId, @RequestBody ProductCreateOrUpdateRequest request) {
    return ApiResponse.success(productService.updateProduct(productId, request));
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<Void> productUpdate(@PathVariable Long productId) {
    return ApiResponse.success(productService.deleteProduct(productId));
  }

}
