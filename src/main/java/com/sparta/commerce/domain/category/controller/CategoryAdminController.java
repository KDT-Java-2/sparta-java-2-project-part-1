package com.sparta.commerce.domain.category.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.category.dto.admin.CategoryCreateRequest;
import com.sparta.commerce.domain.category.dto.admin.CategoryResponse;
import com.sparta.commerce.domain.category.dto.admin.CategoryUpdateRequest;
import com.sparta.commerce.domain.category.service.CategoryService;
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
public class CategoryAdminController {

  private final CategoryService categoryService;

  @PostMapping
  public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
    CategoryResponse response = categoryService.createCategory(request);
    return ApiResponse.success(response);
  }

  @PutMapping("/{categoryId}")
  public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryUpdateRequest request) {
    CategoryResponse response = categoryService.updateCategory(categoryId, request);
    return ApiResponse.success(response);
  }

  @DeleteMapping("/{categoryId}")
  public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ApiResponse.success();
  }

}
