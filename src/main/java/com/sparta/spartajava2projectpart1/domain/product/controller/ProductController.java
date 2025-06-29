package com.sparta.spartajava2projectpart1.domain.product.controller;

import com.sparta.spartajava2projectpart1.common.response.ApiResponse;
import com.sparta.spartajava2projectpart1.domain.product.dto.ProductSearchResponse;
import com.sparta.spartajava2projectpart1.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.wildfly.common.annotation.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<Page<ProductSearchResponse>> findAll(
            @RequestParam Long categoryId,
            @RequestParam Integer minPrice,
            @RequestParam Integer maxPrice,
            Pageable pageable
    ) {
        return ApiResponse.success(productService.findAll(categoryId, minPrice, maxPrice, pageable));
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductSearchResponse> findProductDetails(
            @PathVariable @NotNull Long productId
    ) {
        return ApiResponse.success(productService.findProductDetails(productId));
    }
}
