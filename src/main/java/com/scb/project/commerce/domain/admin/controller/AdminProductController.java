package com.scb.project.commerce.domain.admin.controller;

import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateResponse;
import com.scb.project.commerce.domain.admin.dto.AdminProductUpdateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductUpdateResponse;
import com.scb.project.commerce.domain.admin.service.AdminProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

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
        @Valid @RequestBody AdminProductCreateRequest request) {
        return ApiResponse.success(adminProductService.createProduct(request));
    }

    /**
     * 상품 정보를 수정하는 API
     *
     * <p>요청으로 전달된 필드 중 null이 아닌 값만 선별적으로 업데이트합니다.
     * <br>예: 이름, 가격만 보낼 경우 해당 항목만 수정됩니다.
     * <br>카테고리 ID가 없으면 기존 값 유지합니다.
     *
     * @param productId 수정 대상 상품의 ID
     * @param request   수정할 상품 정보
     * @return 수정된 상품 정보 응답
     */
    @PutMapping("/{productId}")
    public ApiResponse<AdminProductUpdateResponse> updateProduct(
        @PathVariable Long productId,
        @Valid @RequestBody AdminProductUpdateRequest request
    ) {
        return ApiResponse.success(adminProductService.updateProduct(productId, request));
    }

    /**
     * 관리자 상품 삭제 API
     *
     * <p>상품 ID에 해당하는 상품을 삭제합니다.
     * <br>단, 해당 상품이 배송중 또는 배송완료 상태의 주문에 포함된 경우 삭제할 수 없습니다.
     *
     * @param productId 삭제할 상품 ID
     * @return 삭제 성공 여부 응답
     */
    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long productId) {
        adminProductService.deleteProduct(productId);
        return ApiResponse.success();
    }
}
