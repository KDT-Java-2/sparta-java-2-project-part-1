package com.sparta.commerce.domain.product.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.product.dto.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.ProductFindRequest;
import com.sparta.commerce.domain.product.dto.ProductFindResponse;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/search")
  public ApiResponse<Page<ProductFindResponse>> findSearchProducts(@Valid @RequestBody ProductFindRequest request) {
    return ApiResponse.success(productService.findSearchProducts(request));
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductDetailResponse> findProductById(@PathVariable Long productId) {
    return ApiResponse.success(productService.findProductById(productId));
  }

}
