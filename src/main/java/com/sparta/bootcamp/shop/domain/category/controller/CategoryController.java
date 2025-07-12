package com.sparta.bootcamp.shop.domain.category.controller;

import com.sparta.bootcamp.shop.common.response.ApiResponse;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryRequest;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryResponse;
import com.sparta.bootcamp.shop.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/hierarchy")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.success(categoryService.getCategoryTree());
    }
}