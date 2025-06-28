package com.sparta.java2.project.part1.commerce.domain.product.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ApiResponse<Page<ProductSearchResponse>> search(
            @RequestBody ProductSearchRequest productSearchRequest,
            Pageable pageable
    ) {
        return ApiResponse.success(productService.search(productSearchRequest, pageable));
    }
}
