package com.sparta.java_02.domain.product;

import com.sparta.java_02.domain.product.dto.ProductResponse;
import com.sparta.java_02.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 목록 조회 (검색)
     * GET /api/products
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getProducts(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sortBy) {

        // sortBy 파라미터 파싱
        String[] sortParams = sortBy.split(",");
        String sortField = sortParams[0];
        String sortDirection = sortParams.length > 1 ? sortParams[1] : "desc";
        
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        Page<ProductResponse> products = productService.getProducts(category, minPrice, maxPrice, pageable);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    /**
     * 상품 상세 조회
     * GET /api/products/{productId}
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable Long productId) {
        ProductResponse product = productService.getProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(product));
    }
} 