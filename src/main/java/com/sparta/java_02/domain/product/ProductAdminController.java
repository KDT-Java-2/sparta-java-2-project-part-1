package com.sparta.java_02.domain.product;

import com.sparta.java_02.domain.product.dto.ProductCreateRequest;
import com.sparta.java_02.domain.product.dto.ProductResponse;
import com.sparta.java_02.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductService productService;

    /**
     * 상품 등록
     * POST /api/admin/products
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Long>>> createProduct(
            @Valid @RequestBody ProductCreateRequest request) {
        
        Long productId = productService.createProduct(request);
        return ResponseEntity.ok(ApiResponse.success(Map.of("productId", productId)));
    }

    /**
     * 상품 수정
     * PUT /api/admin/products/{productId}
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductCreateRequest request) {
        
        ProductResponse response = productService.updateProduct(productId, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 상품 삭제
     * DELETE /api/admin/products/{productId}
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
} 