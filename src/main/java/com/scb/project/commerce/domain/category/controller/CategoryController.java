package com.scb.project.commerce.domain.category.controller;

import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.category.dto.CategoryResponse;
import com.scb.project.commerce.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 전체 계층 구조 조회 API
     *
     * <p>트리 형태의 카테고리 구조를 조회하는 엔드포인트입니다.</p>
     * <p>최상위 카테고리부터 하위 카테고리까지 계층적으로 포함된 구조로 응답합니다.</p>
     *
     * @return 계층 구조로 구성된 카테고리 목록이 포함된 공통 응답 객체
     */
    @GetMapping("/hierarchy")
    public ApiResponse<List<CategoryResponse>> getCategoryHierarchyList() {
        return ApiResponse.success(categoryService.getCategoryHierarchy());
    }
}
