package org.example.prj_part_1.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.prj_part_1.common.response.ApiResponse;
import org.example.prj_part_1.domain.product.dto.ProductSearchRequest;
import org.example.prj_part_1.domain.product.dto.ProductSearchResponse;
import org.example.prj_part_1.domain.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  @GetMapping
  public ApiResponse<ProductSearchResponse> getProducts(@ModelAttribute ProductSearchRequest request){
    ProductSearchResponse result = productService.searchProducts(request);
    return null;
  }
}
