package com.dogworld.dogdog.api.product;

import com.dogworld.dogdog.api.product.request.ProductRequest;
import com.dogworld.dogdog.api.product.response.ProductResponse;
import com.dogworld.dogdog.domain.product.ProductService;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductAdminController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest request) {
    ProductResponse response =  productService.createProduct(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable Long productId,
                                                  @Valid @RequestBody ProductRequest request) {
    ProductResponse response = productService.updateProduct(productId, request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }


}
