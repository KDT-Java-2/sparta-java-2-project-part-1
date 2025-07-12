package com.sparta.commerce.domain.category.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.commerce.domain.category.dto.CategoryUpdateResponse;
import com.sparta.commerce.domain.category.service.CategoryAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class CategoryAdminController {

  private final CategoryAdminService categoryAdminService;

  @PostMapping()
  public ApiResponse<Long> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
    Long categoryId = categoryAdminService.createCategory(request);
    return ApiResponse.success(categoryId);
  }

  @PostMapping("/{categoryId}")
  public ApiResponse<CategoryUpdateResponse> updateCategory(@Valid @RequestBody CategoryCreateRequest request,
      @PathVariable Long categoryId) {
    CategoryUpdateResponse response = categoryAdminService.updateCategory(request, categoryId);
    return ApiResponse.success(response);
  }

  @DeleteMapping("/{categoryId}")
  public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryAdminService.deleteCategory(categoryId);
    return ApiResponse.success();
  }

}
