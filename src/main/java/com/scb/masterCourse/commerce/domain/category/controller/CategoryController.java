package com.scb.masterCourse.commerce.domain.category.controller;

import com.scb.masterCourse.commerce.common.response.ApiResponse;
import com.scb.masterCourse.commerce.domain.category.dto.CategoryResponse;
import com.scb.masterCourse.commerce.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/hierarchy")
    public ApiResponse<List<CategoryResponse>> findCategoriesHierarchy() {
        return ApiResponse.success(categoryService.findCategoriesHierarchy());
    }
}
