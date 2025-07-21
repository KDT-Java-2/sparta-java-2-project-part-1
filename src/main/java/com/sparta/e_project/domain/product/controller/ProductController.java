package com.sparta.e_project.domain.product.controller;

import com.sparta.e_project.common.response.ApiResponse;
import com.sparta.e_project.domain.product.dto.ProductRequest;
import com.sparta.e_project.domain.product.dto.ProductResponse;
import com.sparta.e_project.domain.product.dto.ProductSearchResponse;
import com.sparta.e_project.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<Page<ProductSearchResponse>> getProducts(ProductRequest request,
      @PageableDefault(size = 10, page = 0, sort = "createdAt,desc") Pageable pageable) {
    return ApiResponse.success(productService.getAllProducts(request, pageable));
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductResponse> getProductById(@PathVariable Long productId) {
    return ApiResponse.success(productService.getProductById(productId));
  }
}
