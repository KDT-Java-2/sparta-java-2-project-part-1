package com.dogworld.dogdog.category.interfaces;

import com.dogworld.dogdog.category.application.CategoryQueryService;
import com.dogworld.dogdog.category.interfaces.dto.response.CategoryResponse;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryQueryService categoryQueryService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
    List<CategoryResponse> response = categoryQueryService.getAllCategories();
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/hierarchy")
  public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategoriesHierarchy() {
    List<CategoryResponse> response = categoryQueryService.getAllCategoriesHierarchy();
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
