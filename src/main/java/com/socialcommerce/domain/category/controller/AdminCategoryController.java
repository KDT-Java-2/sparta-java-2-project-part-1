package com.socialcommerce.domain.category.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.category.dto.CategoryRequest;
import com.socialcommerce.domain.category.dto.CategoryResponse;
import com.socialcommerce.domain.category.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

  private final AdminCategoryService adminCategoryService;

  @PostMapping
  public ApiResponse<Long> createCategory(@RequestBody CategoryRequest request){
    Long categoryId = adminCategoryService.createCategory(request);
    return ApiResponse.success(categoryId);
  }

  @PutMapping("/categoryId")
  public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request){
    CategoryResponse categoryResponse = adminCategoryService.updateCategory(categoryId, request);
    return ApiResponse.success(categoryResponse);
  }
}
