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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ApiResponse<Void> save(@Valid @RequestBody ProductRequest productRequest) {

    productService.save(productRequest);
    return ApiResponse.success();
  }

  @GetMapping
  public ApiResponse<List<ProductResponse>> getAll() {
    return ApiResponse.success(productService.getAll());
  }

  @GetMapping("/{id}")
  public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
    return ApiResponse.success(productService.getById(id));
  }

  @PutMapping("/{id}")
  public ApiResponse<ProductResponse> update(@PathVariable Long id,
      @RequestBody ProductRequest productRequest) {
    return ApiResponse.success(productService.update(id, productRequest));
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable Long id) {
    return ApiResponse.success(productService.delete(id));
  }

  @GetMapping("/by-category?categoryName={categoryName}")
  public ApiResponse<List<CategoryProductDTO>> findCategoryProducts(
      @PathVariable String categoryName) {
    return ApiResponse.success(categoryProductQueryRepository.findCategoryProducts(categoryName));
  }
}
