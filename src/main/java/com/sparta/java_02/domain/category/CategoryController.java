package com.sparta.java_02.domain.category;

import com.sparta.java_02.domain.category.dto.CategoryResponse;
import com.sparta.java_02.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 전체 계층 구조 조회
     * GET /api/categories/hierarchy
     */
    @GetMapping("/hierarchy")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategoryHierarchy() {
        List<CategoryResponse> categories = categoryService.getCategoryHierarchy();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }
} 