package com.sparta.project1.domain.product.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRegisterRequest;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRegisterResponse;
import com.sparta.project1.domain.product.service.admin.ProductAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    //상품등록
    @Transactional
    @PostMapping("/products")
    public ApiResponse<ProductAdminRegisterResponse> productRegister(@Valid @RequestBody ProductAdminRegisterRequest request) {
        return ApiResponse.success(productAdminService.productAdminRegister(request));
    }
}