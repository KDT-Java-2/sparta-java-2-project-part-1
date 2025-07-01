package com.sparta.java2.project.part1.commerce.domain.category.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.category.dto.CategorySearchHierarchyResponse;
import com.sparta.java2.project.part1.commerce.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/hierarchy")
    public ApiResponse<List<CategorySearchHierarchyResponse>> searchHierarchy() {
        return ApiResponse.success(categoryService.searchHierarchy());
    }

}
