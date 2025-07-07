package com.sparta.part_1.domain.product.controller.admin;

import com.sparta.part_1.common.respeonse.ApiResponse;
import com.sparta.part_1.domain.product.dto.request.ProductRequest;
import com.sparta.part_1.domain.product.dto.response.ProductAddResponse;
import com.sparta.part_1.domain.product.dto.response.ProductDeleteResponse;
import com.sparta.part_1.domain.product.dto.response.ProductUpdateResponse;
import com.sparta.part_1.domain.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {

  private final ProductService productService;

  @PostMapping
  public ApiResponse<ProductAddResponse> addProducts(
      @Valid @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.addProduct(request));
  }

  @PutMapping("/{productId}")
  public ApiResponse<ProductUpdateResponse> updateProducts(
      @Valid @RequestBody ProductRequest request,
      @PathVariable @Pattern(regexp = "^[0-9]+$", message = "상품 ID는 숫자로만 이루어져야합니다.")
      Long productId) {
    return ApiResponse.success(productService.updateProduct(request, productId));
  }

  @DeleteMapping("/{productId}")
  public ApiResponse<ProductDeleteResponse> deleteProducts(@PathVariable @NotNull Long productId) {
    return ApiResponse.success(productService.deleteProductProcess(productId));
  }
}
