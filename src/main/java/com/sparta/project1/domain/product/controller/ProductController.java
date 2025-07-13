package com.sparta.project1.domain.product.controller;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.product.dto.ProductDetailResponse;
import com.sparta.project1.domain.product.dto.ProductSearchRequest;
import com.sparta.project1.domain.product.dto.ProductSearchResponse;
import com.sparta.project1.domain.product.entity.Product;
import com.sparta.project1.domain.product.repository.ProductSearchRepository;
import com.sparta.project1.domain.product.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final ProductSearchRepository productSearchRepository;

    //목록조회(검색)
    @GetMapping
    public ApiResponse<Page<Product>> productSearchAll(
        @RequestParam(required = false) Long category,
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortBy
    ) {
        return ApiResponse.success(productSearchRepository.searchProducts(category, minPrice, maxPrice, PageRequest.of(page, size)));
    }

    //상품 상세조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailResponse> productDetailSearch(@PathVariable Long productId) {
        return ApiResponse.success(productService.productDetailSearch(productId));    
    }
}