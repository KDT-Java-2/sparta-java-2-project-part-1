package com.socialcommerce.domain.product.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ApiResponse<Page<ProductResponse>> searchProducts(
      @RequestParam(required = false) Long category,
      @RequestParam(required = false) Integer minPrice,
      @RequestParam(required = false) Integer maxPrice,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "createdAt,desc") String sortBy
  ){
    Page<ProductResponse> result = productService.searchProducts(category, minPrice, maxPrice, page, size, sortBy);
    return ApiResponse.success(result);
  }

  @GetMapping("/productId")
  public ApiResponse<ProductResponse> findProduct(@PathVariable Long productId){
    ProductResponse result = productService.findProduct(productId);
    return ApiResponse.success(result);
  }
}
