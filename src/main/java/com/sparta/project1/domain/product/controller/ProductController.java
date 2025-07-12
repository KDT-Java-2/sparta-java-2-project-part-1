package com.sparta.project1.domain.product.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.product.dto.ProductDetailResponse;
import com.sparta.project1.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;
    
    //상품 상세조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailResponse> productDetailSearch(@PathVariable Long productId) {
        return ApiResponse.success(productService.productDetailSearch(productId));    
    }
}