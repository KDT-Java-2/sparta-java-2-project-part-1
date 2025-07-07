package com.sparta.part_1.domain.category.controller.admin;

import com.sparta.part_1.common.respeonse.ApiResponse;
import com.sparta.part_1.domain.category.dto.request.CategoryAddRequest;
import com.sparta.part_1.domain.category.dto.request.CategoryEditRequest;
import com.sparta.part_1.domain.category.dto.response.CategoryAddResponse;
import com.sparta.part_1.domain.category.dto.response.CategoryEditResponse;
import com.sparta.part_1.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class CategoryAdminController {

  private final CategoryService categoryService;


  @PostMapping
  public ApiResponse<CategoryAddResponse> addCategories(
      @RequestBody @Valid CategoryAddRequest request) {
    return ApiResponse.success(categoryService.addCategories(request));
  }

  @PutMapping("/{categoryId}")
  public ApiResponse<CategoryEditResponse> editCategories(
      @RequestBody @Valid CategoryEditRequest request,
      @PathVariable @NotNull Long categoryId) {

    return ApiResponse.success(categoryService.editCategories(request, categoryId));
  }


  @DeleteMapping("/{categoryId}")
  public ApiResponse<Void> deleteCategories(@PathVariable @NotNull Long categoryId) {
    categoryService.deleteCategories(categoryId);

    return ApiResponse.success();
  }
}
