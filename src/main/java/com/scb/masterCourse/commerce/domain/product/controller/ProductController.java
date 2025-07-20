package com.scb.masterCourse.commerce.domain.product.controller;

import com.scb.masterCourse.commerce.common.response.ApiResponse;
import com.scb.masterCourse.commerce.domain.product.dto.ProductQueryRequest;
import com.scb.masterCourse.commerce.domain.product.dto.ProductQueryResponse;
import com.scb.masterCourse.commerce.domain.product.dto.ProductResponse;
import com.scb.masterCourse.commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<Page<ProductQueryResponse>> findPagedProducts(
        @ModelAttribute ProductQueryRequest request
    ) {
        Pageable pageable = request.toPageable();

        return ApiResponse.success(productService.findPagedProducts(request, pageable));
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> findProduct(@PathVariable Long productId) {
        return ApiResponse.success(productService.findByProductId(productId));
    }
}
