package com.java_project.part_1.domain.category.controller;

import com.java_project.part_1.common.response.ApiResponse;
import com.java_project.part_1.domain.category.dto.CategoryResponse;
import com.java_project.part_1.domain.category.service.CategoryService;
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
  public ApiResponse<List<CategoryResponse>> hierarchy() {
    return ApiResponse.success(categoryService.getCategoryHierarchy());
  }
}
