package com.sparta.ecommerce.domain.admin.controller;

import com.sparta.ecommerce.common.response.ApiResponse;
import com.sparta.ecommerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.ecommerce.domain.category.dto.CategoryCreateResponse;
import com.sparta.ecommerce.domain.category.dto.CategoryInfoResponse;
import com.sparta.ecommerce.domain.category.service.CategoryService;
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

  private final CategoryService categoryService;

  @PostMapping
  public ApiResponse<CategoryCreateResponse> setCategoryInfo(
      @Valid @RequestBody CategoryCreateRequest request) {
    return ApiResponse.success(categoryService.save(request));
  }

  @PutMapping("/{categoryId}")
  public ApiResponse<CategoryInfoResponse> putCategoryInfo(@PathVariable Long categoryId,
      @Valid @RequestBody CategoryCreateRequest request) {
    return ApiResponse.success(categoryService.update(categoryId, request));
  }

  @DeleteMapping("/{categoryId}")
  public ApiResponse<Void> delCategoryInfo(@PathVariable Long categoryId) {
    categoryService.delete(categoryId);

    return ApiResponse.success();
  }
}