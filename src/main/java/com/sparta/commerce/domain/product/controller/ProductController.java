package com.sparta.commerce.domain.product.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.domain.product.dto.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductUpdateRequest;
import com.sparta.commerce.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<Page<ProductSearchResponse>> searchProducts(
      @ModelAttribute ProductSearchRequest request,
      Pageable pageable) {

    Page<ProductSearchResponse> response = productService.searchProducts(request, pageable);
    return ApiResponse.success(response);
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductDetailResponse> findProductDetail(@PathVariable Long productId) {
    log.info("productId : {}", productId);
    ProductDetailResponse response = productService.findProductDetail(productId);
    return ApiResponse.success(response);
  }
}
