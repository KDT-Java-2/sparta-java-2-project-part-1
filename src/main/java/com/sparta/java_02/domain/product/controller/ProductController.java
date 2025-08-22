package com.sparta.java_02.domain.product.controller;

import com.sparta.java_02.common.dto.ApiResponse;
import com.sparta.java_02.domain.product.dto.ProductCreateRequestDto;
import com.sparta.java_02.domain.product.dto.ProductResponseDto;
import com.sparta.java_02.domain.product.dto.ProductUpdateRequestDto;
import com.sparta.java_02.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  // 1. 상품 등록 (POST)
  @PostMapping
  public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(
      @Valid @RequestBody ProductCreateRequestDto requestDto) {
    ProductResponseDto responseDto = productService.createProduct(requestDto);
    ApiResponse<ProductResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED,
        responseDto);
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  // 2. 전체 상품 조회 (GET)
  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProducts() {
    List<ProductResponseDto> responseDtoList = productService.getProducts();
    ApiResponse<List<ProductResponseDto>> apiResponse = ApiResponse.success(responseDtoList);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  // 3. 단일 상품 조회 (GET)
  @GetMapping("/{productId}")
  public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable Long productId) {
    ProductResponseDto responseDto = productService.getProduct(productId);
    ApiResponse<ProductResponseDto> apiResponse = ApiResponse.success(responseDto);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  // 4. 상품 수정 (PUT)
  @PutMapping("/{productId}")
  public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(
      @PathVariable Long productId,
      @Valid @RequestBody ProductUpdateRequestDto requestDto) {
    ProductResponseDto responseDto = productService.updateProduct(productId, requestDto);
    ApiResponse<ProductResponseDto> apiResponse = ApiResponse.success(responseDto);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  // 5. 상품 삭제 (DELETE)
  @DeleteMapping("/{productId}")
  public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    ApiResponse<Void> apiResponse = ApiResponse.success(HttpStatus.NO_CONTENT, null);
    return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
  }
}