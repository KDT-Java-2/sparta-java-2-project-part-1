package com.sparta.bootcamp.java_2_example.domain.category.controller;

import com.sparta.bootcamp.java_2_example.common.response.ApiResponse;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryHierarchyResponse;
import com.sparta.bootcamp.java_2_example.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/hierarchy")
    public ApiResponse<List<CategoryHierarchyResponse>> getAllHierarchy() {
        return ApiResponse.success(categoryService.getAllHierarchy());
    }

}
