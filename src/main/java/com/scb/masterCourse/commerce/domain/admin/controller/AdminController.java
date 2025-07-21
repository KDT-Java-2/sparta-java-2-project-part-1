package com.scb.masterCourse.commerce.domain.admin.controller;

import com.scb.masterCourse.commerce.common.response.ApiResponse;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminCategoryRequest;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminCategoryResponse;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductRequest;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductResponse;
import com.scb.masterCourse.commerce.domain.admin.service.AdminService;
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

    @PostMapping("/products")
    public ApiResponse<AdminProductResponse> createProduct(@Valid @RequestBody AdminProductRequest request) {
        return ApiResponse.success(adminService.createProduct(request));
    }

    @PutMapping("/products/{productId}")
    public ApiResponse<AdminProductResponse> updateProduct(
        @PathVariable Long productId,
        @Valid @RequestBody AdminProductRequest request
    ) {
        return ApiResponse.success(adminService.updateProduct(productId, request));
    }

    @DeleteMapping("/products/{productId}")
    public ApiResponse<Void> deleteProductByAdmin(@PathVariable Long productId) {

        adminService.deleteProduct(productId);

        return ApiResponse.success();
    }

    @PostMapping("/categories")
    public ApiResponse<Long> createCategory(@Valid @RequestBody AdminCategoryRequest request) {
        return ApiResponse.success(adminService.createCategory(request));
    }

    @PutMapping("/categories/{categoryId}")
    public ApiResponse<AdminCategoryResponse> updateCategory(
        @PathVariable Long categoryId,
        @Valid @RequestBody AdminCategoryRequest request
    ) {
        return ApiResponse.success(adminService.updateCategory(categoryId, request));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId) {
        adminService.deleteCategory(categoryId);

        return ApiResponse.success();
    }
}
