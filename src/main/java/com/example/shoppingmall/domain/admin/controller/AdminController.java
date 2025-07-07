package com.example.shoppingmall.domain.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.shoppingmall.common.response.ApiResponse;
import com.example.shoppingmall.domain.category.dto.CategoryCreateRequest;
import com.example.shoppingmall.domain.category.dto.CategoryUpdateRequest;
import com.example.shoppingmall.domain.category.dto.CategoryUpdateResponse;
import com.example.shoppingmall.domain.category.service.CategoryService;
import com.example.shoppingmall.domain.product.dto.ProductCreateRequest;
import com.example.shoppingmall.domain.product.dto.ProductUpdateRequest;
import com.example.shoppingmall.domain.product.service.ProductService;
import com.example.shoppingmall.domain.product.dto.ProductCreateResponse;
import com.example.shoppingmall.domain.product.dto.ProductDeleteResponse;
import com.example.shoppingmall.domain.product.dto.ProductUpdateResponse;
import com.example.shoppingmall.domain.category.dto.CategoryCreateResponse;
import com.example.shoppingmall.domain.category.dto.CategoryDeleteResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;

    // 상품 등록
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductCreateResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        ProductCreateResponse response = productService.createProduct(request);
        return ApiResponse.success(response);
    }

    // 상품 수정
    @PutMapping("/products/{productId}")
    public ApiResponse<ProductUpdateResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductUpdateRequest request) {
        ProductUpdateResponse response = productService.updateProduct(productId, request);
        return ApiResponse.success(response);
    }

    // 상품 삭제
    @DeleteMapping("/products/{productId}")
    public ApiResponse<ProductDeleteResponse> deleteProduct(@PathVariable Long productId) {
        ProductDeleteResponse response = productService.deleteProduct(productId);
        return ApiResponse.success(response);
    }

    // 카테고리 등록
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryCreateResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        CategoryCreateResponse response = categoryService.createCategory(request);
        return ApiResponse.success(response);
    }

    // 카테고리 수정
    @PutMapping("/categories/{categoryId}")
    public ApiResponse<CategoryUpdateResponse> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryUpdateRequest request) {
        CategoryUpdateResponse response = categoryService.updateCategory(categoryId, request);
        return ApiResponse.success(response);
    }

    // 카테고리 삭제
    @DeleteMapping("/categories/{categoryId}")
    public ApiResponse<CategoryDeleteResponse> deleteCategory(@PathVariable Long categoryId) {
        CategoryDeleteResponse response = categoryService.deleteCategory(categoryId);
        return ApiResponse.success(response);
    }
}
