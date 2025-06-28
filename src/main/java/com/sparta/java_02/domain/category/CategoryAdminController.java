package com.sparta.java_02.domain.category;

import com.sparta.java_02.domain.category.dto.CategoryCreateRequest;
import com.sparta.java_02.domain.category.dto.CategoryResponse;
import com.sparta.java_02.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    /**
     * 카테고리 등록
     * POST /api/admin/categories
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Long>>> createCategory(
            @Valid @RequestBody CategoryCreateRequest request) {
        
        Long categoryId = categoryService.createCategory(request);
        return ResponseEntity.ok(ApiResponse.success(Map.of("categoryId", categoryId)));
    }

    /**
     * 카테고리 수정
     * PUT /api/admin/categories/{categoryId}
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryCreateRequest request) {
        
        CategoryResponse response = categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 카테고리 삭제
     * DELETE /api/admin/categories/{categoryId}
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Object>> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
} 