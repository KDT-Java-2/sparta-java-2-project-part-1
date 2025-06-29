package com.scb.project.commerce.domain.admin.controller;

import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryResponse;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryUpdateRequest;
import com.scb.project.commerce.domain.admin.service.AdminCategoryService;
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
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    /**
     * 카테고리 등록 API
     *
     * <p>관리자 권한으로 새로운 카테고리를 등록합니다.</p>
     * <p>부모 카테고리를 지정할 수 있으며, 없을 경우 최상위 카테고리로 등록됩니다.</p>
     *
     * @param request 등록할 카테고리 정보 (이름, 부모 ID)
     * @return 생성된 카테고리 ID
     */
    @PostMapping
    public ApiResponse<Long> createCategory(
        @Valid @RequestBody AdminCategoryCreateRequest request) {
        return ApiResponse.success(adminCategoryService.createCategory(request));
    }

    /**
     * 카테고리 수정 API
     *
     * <p>카테고리명을 수정하거나, 부모 카테고리를 재지정할 수 있습니다.</p>
     * <p>자기 자신을 부모로 지정할 수 없으며, 존재하지 않는 부모 ID는 예외 처리됩니다.</p>
     *
     * @param categoryId 수정할 카테고리 ID
     * @param request    수정할 카테고리 정보 (이름, 부모 ID)
     * @return 수정된 카테고리 정보
     */
    @PutMapping("/{categoryId}")
    public ApiResponse<AdminCategoryResponse> updateCategory(
        @PathVariable Long categoryId,
        @Valid @RequestBody AdminCategoryUpdateRequest request
    ) {
        return ApiResponse.success(adminCategoryService.updateCategory(categoryId, request));
    }

    /**
     * 카테고리 삭제 API
     *
     * <p>해당 카테고리에 속한 하위 카테고리 또는 상품이 없을 경우에만 삭제할 수 있습니다.</p>
     * <p>삭제 조건을 만족하지 않을 경우 예외를 발생시킵니다.</p>
     *
     * @param categoryId 삭제할 카테고리 ID
     * @return 삭제 성공 여부 (응답값은 항상 true)
     */
    @DeleteMapping("/{categoryId}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId) {
        adminCategoryService.deleteCategory(categoryId);
        return ApiResponse.success();
    }
}
