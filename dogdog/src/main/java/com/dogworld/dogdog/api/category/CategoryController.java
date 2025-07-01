package com.dogworld.dogdog.api.category;

import com.dogworld.dogdog.api.category.request.CategoryRequest;
import com.dogworld.dogdog.api.category.response.CategoryResponse;
import com.dogworld.dogdog.domain.category.CategoryService;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
    List<CategoryResponse> response = categoryService.getAllCategories();
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/hierarchy")
  public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategoriesHierarchy() {
    List<CategoryResponse> response = categoryService.getAllCategoriesHierarchy();
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest request) {
    CategoryResponse response = categoryService.createCategory(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
