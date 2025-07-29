package com.sparta.commerce_project_01.domain.product.controller;

import com.sparta.commerce_project_01.common.response.ApiResponse;
import com.sparta.commerce_project_01.domain.category.dto.CategoryProductDTO;
import com.sparta.commerce_project_01.domain.category.repository.CategoryProductQueryRepository;
import com.sparta.commerce_project_01.domain.product.dto.ProductResponse;
import com.sparta.commerce_project_01.domain.product.dto.ProductSearchResponse;
import com.sparta.commerce_project_01.domain.product.repository.ProductQueryRepository;
import com.sparta.commerce_project_01.domain.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  private final ProductQueryRepository productQueryRepository;

  private final CategoryProductQueryRepository categoryProductQueryRepository;

  @GetMapping
  public ApiResponse<Page<ProductSearchResponse>> findAll(
      @RequestParam(required = false) Long categoryId,
      @RequestParam(required = false) Integer minPrice,
      @RequestParam(required = false) Integer maxPrice,
      @PageableDefault(size = 10, sort = "id") Pageable pageable
  ) {
    return ApiResponse.success(productService.findAll(categoryId, minPrice, maxPrice, pageable));
  }


  @GetMapping("/{id}")
  public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
    return ApiResponse.success(productService.getById(id));
  }

  @GetMapping("/by-category?categoryName={categoryName}")
  public ApiResponse<List<CategoryProductDTO>> findCategoryProducts(
      @PathVariable String categoryName) {
    return ApiResponse.success(categoryProductQueryRepository.findCategoryProducts(categoryName));
  }
}