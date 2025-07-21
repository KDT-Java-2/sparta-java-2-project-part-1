package com.sparta.e_project.domain.category.controller;

import com.sparta.e_project.common.response.ApiResponse;
import com.sparta.e_project.domain.category.dto.CategoryResponse;
import com.sparta.e_project.domain.category.service.CategoryService;
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

  @GetMapping("/hierarchy")
  public ApiResponse<List<CategoryResponse>> getCategoryHierarchy() {
    return ApiResponse.success(categoryService.getAllCategoryHierarchy());
  }
}
