package com.sparta.part_1.domain.category.controller;

import com.sparta.part_1.common.respeonse.ApiResponse;
import com.sparta.part_1.domain.category.dto.response.CategoryResponse;
import com.sparta.part_1.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService service;

  @GetMapping("/hierarchy")
  public ApiResponse<List<CategoryResponse>> getAllCategories() {
    return ApiResponse.success(service.getAllCategories());
  }
}
