package com.sparta.javamarket.domain.category.controller;


import com.sparta.javamarket.common.response.ApiResponse;
import com.sparta.javamarket.domain.category.dto.CategoryResponse;
import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.category.service.CategoryService;
import java.util.List;
import java.util.Map;
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
  public ApiResponse<List<CategoryResponse>> categoryHierarchy(){

    return ApiResponse.success(categoryService.getCategoryHierarchy());
  }
}
