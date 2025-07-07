package com.sparta.commerce.domain.product.controller;

import com.sparta.commerce.common.response.ApiResponse;
import com.sparta.commerce.common.response.PagingResponse;
import com.sparta.commerce.domain.product.dto.product.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.product.ProductRequest;
import com.sparta.commerce.domain.product.dto.product.ProductSearchCondition;
import com.sparta.commerce.domain.product.dto.product.ProductSummaryResponse;
import com.sparta.commerce.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  // 상품 등록
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ApiResponse<Long> createProduct(
      @RequestPart("product") @Valid ProductRequest request,
      @RequestPart("file") List<MultipartFile> files) {
    Long productId = productService.createProduct(request, files);
    return ApiResponse.success(productId);
  }

  // 상품 수정
  @PutMapping("/{productId}")
  public ApiResponse<ProductDetailResponse> updateProduct(
      @PathVariable Long productId,
      @RequestPart("product") @Valid ProductRequest request,
      @RequestPart("file") List<MultipartFile> files) {
    ProductDetailResponse productDetailResponse = productService.updateProduct(productId, request, files);
    return ApiResponse.success(productDetailResponse);
  }

  // 상품 삭제
  @DeleteMapping("/{productId}")
  public ApiResponse<?> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return ApiResponse.success();
  }

  // 상품 단건 조회
  @GetMapping("/{productId}")
  public ApiResponse<ProductDetailResponse> getProduct(@PathVariable Long productId) {
    ProductDetailResponse productDetailResponse = productService.getProductItem(productId);
    return ApiResponse.success(productDetailResponse);
  }

  // 상품 목록 조회 (검색 조건 + 페이징)
  @PostMapping("/search")
  public PagingResponse<ProductSummaryResponse> getProducts(@RequestBody ProductSearchCondition condition, Pageable pageable) {
    return  productService.searchProducts(condition, pageable);
  }
}

