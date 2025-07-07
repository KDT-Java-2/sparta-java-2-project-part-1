package com.example.shoppingmall.domain.category.controller;

import com.example.shoppingmall.common.response.ApiResponse;
import com.example.shoppingmall.domain.category.dto.CategoryHierarchyResponse;
import com.example.shoppingmall.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/hierarchy")
    public ApiResponse<List<CategoryHierarchyResponse>> getCategoryHierarchy() {
        return ApiResponse.success(categoryService.getCategoryHierarchy());
    }
}
