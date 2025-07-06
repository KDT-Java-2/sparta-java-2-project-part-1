package com.sparta.e_project.domain.product.controller;

import com.sparta.e_project.common.response.ApiResponse;
import com.sparta.e_project.domain.product.dto.ProductRequest;
import com.sparta.e_project.domain.product.dto.ProductResponse;
import com.sparta.e_project.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<Page<ProductResponse>> getProducts(
      @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.findPagedProducts(request));
  }
}
