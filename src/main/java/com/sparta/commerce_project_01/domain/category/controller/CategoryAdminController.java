package com.sparta.commerce_project_01.domain.category.controller;

import com.sparta.commerce_project_01.common.response.ApiResponse;
import com.sparta.commerce_project_01.domain.category.dto.CategoryRequest;
import com.sparta.commerce_project_01.domain.category.service.CategoryService;
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
  public ApiResponse<Long> save(@Valid @RequestBody CategoryRequest request) {
    return ApiResponse.success(categoryService.save(request));
  }

  @PutMapping("/{categoryId}")
  public ApiResponse<Void> update(@Valid @PathVariable Long categoryId,
      @RequestBody CategoryRequest request) {
    categoryService.update(categoryId, request);
    return ApiResponse.success();
  }

  @DeleteMapping("/{categoryId}")
  public ApiResponse<Void> delete(@Valid @PathVariable Long categoryId) {
    categoryService.delete(categoryId);
    return ApiResponse.success();
  }
}
