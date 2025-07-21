package com.sparta.e_project.domain.product.controller.admin;

import com.sparta.e_project.common.response.ApiResponse;
import com.sparta.e_project.domain.product.dto.admin.AdminProductRequest;
import com.sparta.e_project.domain.product.dto.admin.AdminProductResponse;
import com.sparta.e_project.domain.product.dto.admin.AdminProductUpdateResponse;
import com.sparta.e_project.domain.product.service.admin.AdminProductService;
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
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

  private final AdminProductService adminProductService;

  @PostMapping
  public ApiResponse<AdminProductResponse> addProduct(
      @Valid @RequestBody AdminProductRequest adminProductRequest) {
    AdminProductResponse products = adminProductService.createProducts(adminProductRequest);
    return ApiResponse.success(products);
  }

  @PutMapping("/{productId}")
  public ApiResponse<AdminProductUpdateResponse> updateProduct(@PathVariable Long productId,
      @Valid @RequestBody AdminProductRequest updateRequest) {
    return ApiResponse.success(adminProductService.updateProduct(productId, updateRequest));
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<Void> updateProduct(@PathVariable Long productId) {
    adminProductService.deleteProduct(productId);
    return ApiResponse.success();
  }
}
