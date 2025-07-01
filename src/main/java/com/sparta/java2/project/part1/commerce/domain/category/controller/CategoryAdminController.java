package com.sparta.java2.project.part1.commerce.domain.category.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> create(
            @Valid @RequestBody CategoryCreateRequest categoryCreateRequest
    ) {
        return ApiResponse.success(categoryService.createCategory(categoryCreateRequest));
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<?> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryCreateRequest categoryCreateRequest
    ) {
        return ApiResponse.success(categoryService.updateCategory(categoryId, categoryCreateRequest));
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<?> deleteCategory(
            @PathVariable Long categoryId
    ) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.success();
    }
}
