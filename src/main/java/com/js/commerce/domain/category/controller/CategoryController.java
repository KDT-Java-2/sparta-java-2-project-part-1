package com.js.commerce.domain.category.controller;

import com.js.commerce.common.response.ApiResponse;
import com.js.commerce.domain.category.dto.CategoryHierarchyDto;
import com.js.commerce.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public ApiResponse<List<CategoryHierarchyDto>> getCategoryHierarchy() {
    List<CategoryHierarchyDto> hierarchy = categoryService.getCategoryHierarchy();
    return ApiResponse.success(hierarchy);
  }
}
