package com.sparta.commerce.domain.category.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.category.dto.CategoryRequest;
import com.sparta.commerce.domain.category.dto.CategoryResponse;
import com.sparta.commerce.domain.category.dto.CategoryTreeDto;
import com.sparta.commerce.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
  private final CategoryService categoryService;

  // 카테고리 등록
  @PostMapping
  public ApiResponse<Long> createProduct(@RequestBody @Valid CategoryRequest request) {
    Long categoryId = categoryService.create(request);
    return ApiResponse.success(categoryId);
  }

  // 카테고리 수정
  @PutMapping("/{categoryId}")
  public ApiResponse<CategoryResponse> updateProduct(@PathVariable Long categoryId,
      @RequestBody @Valid CategoryRequest request) {
    CategoryResponse response = categoryService.update(categoryId, request);
    return ApiResponse.success(response);
  }

  // 카테고리 삭제
  @DeleteMapping("/{categoryId}")
  public ApiResponse<?> deleteProduct(@PathVariable Long categoryId) {
    categoryService.delete(categoryId);
    return ApiResponse.success();
  }

  // 카테고리 목록 조회
  @GetMapping
  public ApiResponse<List> getProduct() {
    List<CategoryTreeDto> allCategory = categoryService.getAllCategory();
    return ApiResponse.success(allCategory);
  }
}
