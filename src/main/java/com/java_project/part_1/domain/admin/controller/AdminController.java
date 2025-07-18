package com.java_project.part_1.domain.admin.controller;

import com.java_project.part_1.common.response.ApiResponse;
import com.java_project.part_1.domain.admin.service.AdminService;
import com.java_project.part_1.domain.category.dto.CategoryRequest;
import com.java_project.part_1.domain.category.entity.Category;
import com.java_project.part_1.domain.product.dto.ProductCreateRequest;
import com.java_project.part_1.domain.product.entity.Product;
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
@RequestMapping("/api/admin")
public class AdminController {

  private final AdminService adminService;

  @PostMapping("/categories")
  public ApiResponse<Category> createCategory(@Valid @RequestBody CategoryRequest request) {
    adminService.createCategory(request);
    return ApiResponse.success();
  }

  @PutMapping("/categories/{categoryId}")
  public ApiResponse<Category> updateCategory(@PathVariable Long categoryId,
      @Valid @RequestBody CategoryRequest request) {
    adminService.updateCategory(categoryId, request);
    return ApiResponse.success();
  }

  @DeleteMapping("/categories/{categoryId}")
  public ApiResponse<Category> deleteCategory(@PathVariable Long categoryId) {
    adminService.deleteCategory(categoryId);
    return ApiResponse.success();
  }

  @PostMapping("/products")
  public ApiResponse<Product> createProduct(@Valid @RequestBody ProductCreateRequest request) {
    return ApiResponse.success(adminService.createProduct(request));
  }

  @PutMapping("/products/{productId}")
  public ApiResponse<Product> updateProduct(@PathVariable Long productId,
      @Valid @RequestBody ProductCreateRequest request) {
    return ApiResponse.success(adminService.updateProduct(productId, request));
  }

  @DeleteMapping("/products/{productId}")
  public ApiResponse<Product> deleteProduct(@PathVariable Long productId) {
    adminService.deleteProduct(productId);
    return ApiResponse.success();
  }
}
