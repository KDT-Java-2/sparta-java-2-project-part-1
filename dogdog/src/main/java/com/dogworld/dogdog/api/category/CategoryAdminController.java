package com.dogworld.dogdog.api.category;

import com.dogworld.dogdog.api.category.request.CategoryRequest;
import com.dogworld.dogdog.api.category.response.CategoryCreateResponse;
import com.dogworld.dogdog.domain.category.CategoryService;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}

