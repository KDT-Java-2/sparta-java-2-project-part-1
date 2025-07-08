package com.sparta.coupang_commerce.domain.product.controller;

import com.sparta.coupang_commerce.common.response.ApiResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductPageRequest;
import com.sparta.coupang_commerce.domain.product.dto.ProductPageResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductResponse;
import com.sparta.coupang_commerce.domain.product.service.ProductService;
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
  public ApiResponse<Page<ProductPageResponse>> getProductPageResponse(ProductPageRequest request, @PageableDefault(size = 10, page = 0, sort = "createdAt,desc") Pageable pageable) {
    return ApiResponse.success(productService.getProductPageResponse(request, pageable));
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductResponse> getProductDetailResponse(@PathVariable Long productId) {
    return ApiResponse.success(productService.getProductResponse(productId));
  }
}
