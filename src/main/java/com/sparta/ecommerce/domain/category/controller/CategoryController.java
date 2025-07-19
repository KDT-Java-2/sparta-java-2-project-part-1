package com.sparta.ecommerce.domain.category.controller;

import com.sparta.ecommerce.common.response.ApiResponse;
import com.sparta.ecommerce.domain.category.dto.CategoryResponse;
import com.sparta.ecommerce.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/hierarchy")
  public ApiResponse<List<CategoryResponse>> getCategoryList() {
    return ApiResponse.success(categoryService.getCategoryTree());
  }
}