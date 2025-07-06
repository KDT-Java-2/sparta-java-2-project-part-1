package com.sparta.javamarket.domain.admin.controller;

import com.sparta.javamarket.common.response.ApiResponse;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateResponse;
import com.sparta.javamarket.domain.admin.service.AdminCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ApiResponse<AdminCategoryCreateResponse> createAdminCategory(@Valid @RequestBody AdminCategoryCreateRequest request) {
    return ApiResponse.success(adminCategoryService.adminCreateCategory(request));
  }

  @PutMapping("/{categoryId}")
  public ApiResponse<AdminCategoryCreateResponse> updateAdminCategory(@PathVariable Long categoryId,  @Valid @RequestBody AdminCategoryCreateRequest request) {
    return ApiResponse.success(adminCategoryService.adminUpdateCategory(categoryId, request));
  }

  @DeleteMapping("/{categoryId}")
  public ApiResponse<Void> deleteAdminCategory(@PathVariable Long categoryId) {
    adminCategoryService.adminDeleteCategory(categoryId);
    return ApiResponse.success();
  }

}
