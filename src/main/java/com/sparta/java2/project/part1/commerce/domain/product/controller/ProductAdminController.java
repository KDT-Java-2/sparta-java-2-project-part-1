package com.sparta.java2.project.part1.commerce.domain.product.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductAdminController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> createProduct(
            @Valid @RequestBody ProductCreateRequest productCreateRequest
    ) {
        return ApiResponse.success(productService.createProduct(productCreateRequest));
    }

    @PutMapping("/{productId}")
    public ApiResponse<?> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductCreateRequest productUpdateRequest
    ) {
        return ApiResponse.success(productService.updateProduct(productId, productUpdateRequest));
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<?> deleteProduct(
            @PathVariable Long productId
    ) {
        productService.deleteProduct(productId);
        return ApiResponse.success();
    }
}
