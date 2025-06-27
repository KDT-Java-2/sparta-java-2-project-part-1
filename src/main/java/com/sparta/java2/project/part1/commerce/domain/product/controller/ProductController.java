package com.sparta.java2.project.part1.commerce.domain.product.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.product.dto.CategoryProductDto;
import com.sparta.java2.project.part1.commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("by-category")
    public ApiResponse<Page<CategoryProductDto>> findByCategory(
            @RequestParam String categoryName,
            Pageable pageable) {
        return ApiResponse.success(productService.findCategoryProductByName(categoryName, pageable));
    }
}
