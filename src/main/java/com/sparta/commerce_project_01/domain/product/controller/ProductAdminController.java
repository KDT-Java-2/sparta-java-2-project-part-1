package com.sparta.commerce_project_01.domain.product.controller;

import com.sparta.commerce_project_01.common.response.ApiResponse;
import com.sparta.commerce_project_01.domain.category.repository.CategoryProductQueryRepository;
import com.sparta.commerce_project_01.domain.product.dto.ProductRequest;
import com.sparta.commerce_project_01.domain.product.dto.ProductResponse;
import com.sparta.commerce_project_01.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductAdminController {

  private final ProductService productService;

  private final CategoryProductQueryRepository categoryProductQueryRepository;

  // ADMIN API
  @PostMapping
  public ApiResponse<ProductResponse> save(@Valid @RequestBody ProductRequest productRequest) {
    return ApiResponse.success(productService.save(productRequest));
  }

  @PutMapping("/{id}")
  public ApiResponse<ProductResponse> update(@PathVariable Long id,
      @RequestBody ProductRequest productRequest) {
    return ApiResponse.success(productService.update(id, productRequest));
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ApiResponse.success();
  }

}