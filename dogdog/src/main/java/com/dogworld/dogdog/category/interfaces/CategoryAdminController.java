package com.dogworld.dogdog.category.interfaces;

import com.dogworld.dogdog.category.application.CategoryCommandService;
import com.dogworld.dogdog.category.interfaces.dto.request.CategoryRequest;
import com.dogworld.dogdog.category.interfaces.dto.response.CategoryCreateResponse;
import com.dogworld.dogdog.category.interfaces.dto.response.CategoryResponse;
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

  private final CategoryCommandService categoryCommandService;

  @PostMapping
  public ResponseEntity<ApiResponse<CategoryCreateResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
    CategoryCreateResponse response = categoryCommandService.createCategory(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long categoryId,
      @Valid @RequestBody CategoryRequest request) {
      CategoryResponse response = categoryCommandService.updateCategory(categoryId, request);
      return ResponseEntity.ok(ApiResponse.success(response));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long categoryId) {
    categoryCommandService.deleteCategory(categoryId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}

