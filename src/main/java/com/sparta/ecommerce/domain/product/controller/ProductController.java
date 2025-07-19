package com.sparta.ecommerce.domain.product.controller;

import com.sparta.ecommerce.common.response.ApiResponse;
import com.sparta.ecommerce.domain.product.dto.ProductInfoResponse;
import com.sparta.ecommerce.domain.product.dto.ProductListResponse;
import com.sparta.ecommerce.domain.product.dto.ProductSearchRequest;
import com.sparta.ecommerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<Page<ProductListResponse>> getProductPageList(
      @ModelAttribute ProductSearchRequest request,
      Pageable pageable) {
    return ApiResponse.success(productService.getProductPageList(request, pageable));
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductInfoResponse> getById(@PathVariable Long productId) {
    return ApiResponse.success(productService.getById(productId));
  }
}