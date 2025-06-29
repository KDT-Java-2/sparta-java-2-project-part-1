package com.scb.project.commerce.domain.admin.controller;

import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateResponse;
import com.scb.project.commerce.domain.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

    // 상품 등록 API

    /**
     * 상품 등록 API
     *
     * <p>관리자가 새로운 상품을 등록합니다.</p>
     * <p>중복 상품명, 음수 값, 존재하지 않는 카테고리 여부도 검증합니다.</p>
     *
     * @param request 상품 상세 정보가 포함된 상품 DTO
     * @return 등록한 상품의 ID가 포함된 공통 응답 객체
     */
    @PostMapping
    public ApiResponse<AdminProductCreateResponse> createProduct(
        @RequestBody AdminProductCreateRequest request) {
        return ApiResponse.success(adminProductService.createProduct(request));
    }
}
