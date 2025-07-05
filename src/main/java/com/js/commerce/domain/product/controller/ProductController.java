package com.js.commerce.domain.product.controller;

import com.js.commerce.common.response.ApiResponse;
import com.js.commerce.domain.product.dto.ProductDetailResponse;
import com.js.commerce.domain.product.dto.ProductSearchCondition;
import com.js.commerce.domain.product.dto.ProductSearchPagedResponse;
import com.js.commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<ProductSearchPagedResponse> getProducts(
      @ModelAttribute ProductSearchCondition condition) {
    return ApiResponse.success(productService.searchProducts(condition));
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductDetailResponse> getProductDetail(
      @PathVariable Long productId) {
    return ApiResponse.success(productService.getProductDetail(productId));
  }

}
