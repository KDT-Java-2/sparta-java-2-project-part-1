package com.sparta.commerce.domain.category.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.category.dto.CategoryTreeResponse;
import com.sparta.commerce.domain.category.service.CategoryService;
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
  public ApiResponse<List<CategoryTreeResponse>> getCategoryHierarchy() {
    return ApiResponse.success(categoryService.findAllCategories());
  }

}
