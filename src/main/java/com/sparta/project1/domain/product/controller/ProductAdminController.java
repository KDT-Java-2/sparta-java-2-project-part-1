package com.sparta.project1.domain.product.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRequest;
import com.sparta.project1.domain.product.dto.admin.ProductAdminResponse;
import com.sparta.project1.domain.product.service.admin.ProductAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    //상품등록
    @PostMapping("/products")
    public ApiResponse<ProductAdminResponse> productRegister(@Valid @RequestBody ProductAdminRequest request) {
        return ApiResponse.success(productAdminService.productAdminRegister(request));
    }

    //상품수정
    @PutMapping("/products/{productId}")
    public ApiResponse<ProductAdminResponse> productUpdate(@PathVariable("productId") Long productId, @Valid @RequestBody ProductAdminRequest request) {
        return ApiResponse.success(productAdminService.productAdminUpdate(productId, request));
    }

}