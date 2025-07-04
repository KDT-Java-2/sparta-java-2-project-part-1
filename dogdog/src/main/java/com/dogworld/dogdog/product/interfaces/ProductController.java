package com.dogworld.dogdog.product.interfaces;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.dogworld.dogdog.product.application.ProductQueryService;
import com.dogworld.dogdog.product.interfaces.dto.request.ProductSearchCondition;
import com.dogworld.dogdog.product.interfaces.dto.response.ProductResponse;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductQueryService productQueryService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
    List<ProductResponse> products = productQueryService.getAllProducts();
    return ResponseEntity.ok(ApiResponse.success(products));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponse<Page<ProductResponse>>> searchProducts(
      @ModelAttribute ProductSearchCondition condition,
      @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable) {
    Page<ProductResponse> products = productQueryService.searchProducts(condition, pageable);
    return ResponseEntity.ok(ApiResponse.success(products));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long productId) {
    ProductResponse response = productQueryService.getProductById(productId);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
