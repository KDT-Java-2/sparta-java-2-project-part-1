package com.sparta.coupang_commerce.domain.category.controller;

import com.sparta.coupang_commerce.common.response.ApiResponse;
import com.sparta.coupang_commerce.domain.category.dto.CategoryResponse;
import com.sparta.coupang_commerce.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/hierarchy")
  public ApiResponse<List<CategoryResponse>> getCategoryHierarchy() {
    return ApiResponse.success(categoryService.getCategoryHierarchy());
  }
}
