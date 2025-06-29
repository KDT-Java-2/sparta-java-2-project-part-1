package com.sparta.spartajava2projectpart1.domain.product.controller;

import com.sparta.spartajava2projectpart1.common.response.ApiResponse;
import com.sparta.spartajava2projectpart1.domain.product.dto.CategoryCreateRequest;
import com.sparta.spartajava2projectpart1.domain.product.dto.CategoryUpdateRequest;
import com.sparta.spartajava2projectpart1.domain.product.dto.ProductSearchResponse;
import com.sparta.spartajava2projectpart1.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<Void> addProduct(@RequestBody CategoryCreateRequest request){
        productService.addProduct(request);
        return ApiResponse.success();
    }

    @PutMapping
    public ApiResponse<ProductSearchResponse> updateProduct(@RequestBody CategoryUpdateRequest request){
        productService.updateProduct(request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ApiResponse.success();
    }
}
