package me.chahyunho.projectweek1.domain.category.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.response.ApiResponse;
import me.chahyunho.projectweek1.domain.category.dto.CategoryRequest;
import me.chahyunho.projectweek1.domain.category.dto.CategorySearchResponse;
import me.chahyunho.projectweek1.domain.category.service.CategoryService;
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


  @GetMapping("/hierarchy")
  public ApiResponse<List<CategorySearchResponse>> searchCategoryHierarchy() {
    return ApiResponse.success(categoryService.searchCategoryHierarchy());
  }

  @PostMapping
  public ApiResponse<Void> create(@Valid @RequestBody CategoryRequest request) {
    categoryService.create(request);
    return ApiResponse.success();
  }
}
