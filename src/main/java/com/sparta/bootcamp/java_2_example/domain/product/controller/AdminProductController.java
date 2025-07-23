package com.sparta.bootcamp.java_2_example.domain.product.controller;

import com.sparta.bootcamp.java_2_example.common.response.ApiResponse;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductUpdateRequest;
import com.sparta.bootcamp.java_2_example.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductCreateResponse> save(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        return ApiResponse.success(productService.save(request));
    }

    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> update(
            @PathVariable Long productId,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        return ApiResponse.success(productService.update(productId, request));
    }

}
