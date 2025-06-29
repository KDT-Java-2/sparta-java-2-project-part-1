package com.sparta.commerce_project_01.domain.product.controller;

import com.sparta.commerce_project_01.common.response.ApiResponse;
import com.sparta.commerce_project_01.domain.category.dto.CategoryProductDTO;
import com.sparta.commerce_project_01.domain.category.repository.CategoryProductQueryRepository;
import com.sparta.commerce_project_01.domain.product.dto.ProductRequest;
import com.sparta.commerce_project_01.domain.product.dto.ProductResponse;
import com.sparta.commerce_project_01.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  private final CategoryProductQueryRepository categoryProductQueryRepository;

  @PostMapping
  public ApiResponse<ProductResponse> save(@Valid @RequestBody ProductRequest productRequest) {
    return ApiResponse.success(productService.save(productRequest));
  }

  @GetMapping("/by-category?categoryName={categoryName}")
  public ApiResponse<List<CategoryProductDTO>> findCategoryProducts(
      @PathVariable String categoryName) {
    return ApiResponse.success(categoryProductQueryRepository.findCategoryProducts(categoryName));
  }
  // CRUD method ...
}
