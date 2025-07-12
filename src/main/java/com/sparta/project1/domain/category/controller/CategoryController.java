package com.sparta.project1.domain.category.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.category.dto.CategoryRequest;
import com.sparta.project1.domain.category.dto.CategoryResponse;
import com.sparta.project1.domain.category.entity.Category;
import com.sparta.project1.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class CategoryController {

  private final CategoryService categoryService;

  //등록
  @PostMapping
  public ApiResponse<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
    return ApiResponse.success(categoryService.create(request));
  }

  //수정
  @PutMapping("/{categoryId}")
  public ApiResponse<Category> update(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest request) {
    return ApiResponse.success(categoryService.update(categoryId, request));
  }
}
