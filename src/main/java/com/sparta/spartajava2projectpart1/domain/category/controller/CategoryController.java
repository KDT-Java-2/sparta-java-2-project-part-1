package com.sparta.spartajava2projectpart1.domain.category.controller;

import com.sparta.spartajava2projectpart1.common.response.ApiResponse;
import com.sparta.spartajava2projectpart1.domain.category.dto.CategoryResponse;
import com.sparta.spartajava2projectpart1.domain.category.service.CategoryService;
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
    public ApiResponse<List<CategoryResponse>> categoryHierarchy(
    ) {
        return ApiResponse.success(categoryService.getCategoriesWithHierarchy());
    }


}
