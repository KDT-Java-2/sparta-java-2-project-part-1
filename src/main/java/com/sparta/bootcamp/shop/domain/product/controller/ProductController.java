package com.sparta.bootcamp.shop.domain.product.controller;

import com.sparta.bootcamp.shop.common.response.ApiResponse;
import com.sparta.bootcamp.shop.domain.product.dto.ProductRequest;
import com.sparta.bootcamp.shop.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.shop.domain.product.dto.ProductSearchRequest;
import com.sparta.bootcamp.shop.domain.product.service.ProductService;
import jakarta.validation.Valid;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<Page<ProductResponse>> getAll(ProductSearchRequest searchRequest, Pageable pageable) {
        return ApiResponse.success(productService.getAll(searchRequest, pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(productService.getById(id));
    }

    @PostMapping
    public ApiResponse<Void> create(@Valid @RequestBody ProductRequest request) {
        productService.save(request);
        return ApiResponse.success();
    }


}