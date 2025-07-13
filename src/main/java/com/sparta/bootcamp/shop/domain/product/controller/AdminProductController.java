package com.sparta.bootcamp.shop.domain.product.controller;

import com.sparta.bootcamp.shop.common.response.ApiResponse;
import com.sparta.bootcamp.shop.domain.product.dto.ProductRequest;
import com.sparta.bootcamp.shop.domain.product.service.AdminProductService;
import com.sparta.bootcamp.shop.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

    @PostMapping
    public ApiResponse<Void> create(@Valid @RequestBody ProductRequest request) {
        adminProductService.save(request);
        return ApiResponse.success();
    }

    @PutMapping("/{productId}")
    public ApiResponse<Void> edit(@PathVariable Long productId, @Valid @RequestBody ProductRequest request) {
        adminProductService.edit(productId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> delete(@PathVariable Long productId) {
        adminProductService.delete(productId);
        return ApiResponse.success();
    }


}
