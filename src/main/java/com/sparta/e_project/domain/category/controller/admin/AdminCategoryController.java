package com.sparta.e_project.domain.category.controller.admin;


import com.sparta.e_project.common.response.ApiResponse;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryCreateRequest;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryCreateResponse;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryUpdateRequest;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryUpdateResponse;
import com.sparta.e_project.domain.category.service.admin.AdminCategoryService;
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
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

  private final AdminCategoryService adminCategoryService;

  @PostMapping
  public ApiResponse createCategory(@Valid @RequestBody AdminCategoryCreateRequest createRequest) {
    AdminCategoryCreateResponse category = adminCategoryService.createCategory(createRequest);
    return ApiResponse.success(category);
  }

  @PutMapping("/{categoryId}")
  public ApiResponse updateCategory(@PathVariable Long categoryId,
      @Valid @RequestBody AdminCategoryUpdateRequest updateRequest) {
    AdminCategoryUpdateResponse category = adminCategoryService.updateCategory(categoryId,
        updateRequest);
    return ApiResponse.success(category);
  }

  @DeleteMapping("/{categoryId}")
  public ApiResponse deleteCategory(@PathVariable Long categoryId) {
    adminCategoryService.deleteCategory(categoryId);
    return ApiResponse.success();
  }

}
