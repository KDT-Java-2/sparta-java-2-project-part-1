package com.java_project.part_1.domain.product.controller;

import com.java_project.part_1.common.response.ApiResponse;
import com.java_project.part_1.domain.product.dto.ProductSearchResponse;
import com.java_project.part_1.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<Page<ProductSearchResponse>> searchProduct(
      @RequestParam Long categoryId,
      @RequestParam Integer minPrice,
      @RequestParam Integer maxPrice,
      Pageable pageable
  ) {
    return ApiResponse.success(
        productService.searchProduct(categoryId, minPrice, maxPrice, pageable));
  }
}
