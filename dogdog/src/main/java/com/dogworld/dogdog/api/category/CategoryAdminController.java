package com.dogworld.dogdog.api.category;

import com.dogworld.dogdog.api.category.request.CategoryRequest;
import com.dogworld.dogdog.api.category.response.CategoryCreateResponse;
import com.dogworld.dogdog.api.category.response.CategoryResponse;
import com.dogworld.dogdog.domain.category.CategoryService;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ApiResponse<CategoryCreateResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
    CategoryCreateResponse response = categoryService.createCategory(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long categoryId,
      @Valid @RequestBody CategoryRequest request) {
      CategoryResponse response = categoryService.updateCategory(categoryId, request);
      return ResponseEntity.ok(ApiResponse.success(response));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}

