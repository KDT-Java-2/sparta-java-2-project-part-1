package com.sparta.javamarket.domain.product.controller;

import com.sparta.javamarket.common.response.ApiResponse;
import com.sparta.javamarket.domain.product.dto.ProductResponse;
import com.sparta.javamarket.domain.product.dto.ProductSearchRequest;
import com.sparta.javamarket.domain.product.dto.ProductSearchResponse;
import com.sparta.javamarket.domain.product.entity.Product;
import com.sparta.javamarket.domain.product.mapper.ProductMapper;
import com.sparta.javamarket.domain.product.repository.ProductQueryRepository;
import com.sparta.javamarket.domain.product.repository.ProductRepository;
import com.sparta.javamarket.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  private final ProductQueryRepository productQueryRepository;


  @GetMapping
  public ApiResponse<Page<ProductResponse>> getAllProduct(@Valid @RequestBody ProductSearchRequest productSearchRequest) {
//    return ApiResponse.success(productService.getAllProducts());

    System.out.println("Finding all products for search request");
    System.out.println("Search request parameters: " + productSearchRequest);
    return ApiResponse.success(productQueryRepository.findAllProducts(productSearchRequest));
  }

  @GetMapping("/{productId}")
  public ApiResponse<ProductSearchResponse> getProductById(@PathVariable long productId) {
    return ApiResponse.success(productService.getProductById(productId));
  }

  @PostMapping
  public ApiResponse<ProductSearchResponse> createProduct(@Valid @RequestBody Product product) {
    return ApiResponse.success();
  }


}
