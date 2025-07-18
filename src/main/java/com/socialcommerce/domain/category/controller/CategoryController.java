package com.socialcommerce.domain.category.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.category.dto.CategoryTreeResponse;
import com.socialcommerce.domain.category.service.CategoryService;
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
  public ApiResponse<CategoryTreeResponse> getCategoryHierarchy(){
    CategoryTreeResponse categoryTreeResponse = categoryService.getCategoryHierarchy();
    return ApiResponse.success(categoryTreeResponse);
  }
}
