package com.example.shoppingmall.domain.product.controller;

import com.example.shoppingmall.common.response.ApiResponse;
import com.example.shoppingmall.domain.product.dto.ProductDetailResponse;
import com.example.shoppingmall.domain.product.dto.ProductListResponse;
import com.example.shoppingmall.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<Page<ProductListResponse>> searchProducts(
            @RequestParam(value = "category", required = false) Long categoryId,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductListResponse> result = productService.searchProducts(categoryId, minPrice, maxPrice, sortBy, pageable);
        return ApiResponse.success(result);
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailResponse> getProductDetail(@PathVariable Long productId) {
        ProductDetailResponse result = productService.getProductDetail(productId);
        return ApiResponse.success(result);
    }
}
