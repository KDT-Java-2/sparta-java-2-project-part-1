package com.sparta.coupang_commerce.domain.category.controller.admin;

import com.sparta.coupang_commerce.common.response.ApiResponse;
import com.sparta.coupang_commerce.domain.category.dto.CategoryResponse;
import com.sparta.coupang_commerce.domain.category.dto.admin.CategoryCreateOrUpdateRequest;
import com.sparta.coupang_commerce.domain.category.dto.admin.CategoryCreateResponse;
import com.sparta.coupang_commerce.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ApiResponse<CategoryCreateResponse> addNewCategory(@Valid @RequestBody CategoryCreateOrUpdateRequest request) {
    return ApiResponse.success(categoryService.createCategory(request));
  }

  @PutMapping({"/{categoryId}"})
  public ApiResponse<List<CategoryResponse>> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryCreateOrUpdateRequest request) {
    return ApiResponse.success(categoryService.updateCategory(categoryId, request));
  }

  @DeleteMapping({"/{categoryId}"})
  public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId) {
    return ApiResponse.success(categoryService.deleteCategory(categoryId));
  }
}
