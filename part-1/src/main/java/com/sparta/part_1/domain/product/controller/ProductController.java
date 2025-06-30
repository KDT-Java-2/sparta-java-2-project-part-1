package com.sparta.part_1.domain.product.controller;

import com.sparta.part_1.common.respeonse.ApiResponse;
import com.sparta.part_1.domain.product.dto.request.ProductSearchRequest;
import com.sparta.part_1.domain.product.dto.response.ProductResponse;
import com.sparta.part_1.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wildfly.common.annotation.NotNull;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ApiResponse<Page<ProductResponse>> findProducts(ProductSearchRequest request, Pageable pageable) {
        return ApiResponse.success(service.findProducts(request, pageable));
    }

    @GetMapping("/{productId}")
    public ApiResponse<List<ProductResponse>> findProductById(@NotNull @PathVariable Long productId) {
        return ApiResponse.success(service.findProductById(productId));
    }

}
