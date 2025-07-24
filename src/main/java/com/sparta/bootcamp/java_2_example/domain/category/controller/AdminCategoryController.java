package com.sparta.bootcamp.java_2_example.domain.category.controller;

import com.sparta.bootcamp.java_2_example.common.response.ApiResponse;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryResponse;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryUpdateRequest;
import com.sparta.bootcamp.java_2_example.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryCreateResponse> save(
            @Valid @RequestBody CategoryCreateRequest request
    ) {
        return ApiResponse.success(categoryService.save(request));
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> update(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryUpdateRequest request
    ) {
        return ApiResponse.success(categoryService.update(categoryId, request));
    }

}
