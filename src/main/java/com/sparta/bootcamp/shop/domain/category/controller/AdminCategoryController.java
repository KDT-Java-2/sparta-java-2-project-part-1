package com.sparta.bootcamp.shop.domain.category.controller;

import com.sparta.bootcamp.shop.common.response.ApiResponse;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryRequest;
import com.sparta.bootcamp.shop.domain.category.service.AdminCategoryService;
import com.sparta.bootcamp.shop.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PostMapping
    public ApiResponse<Void> save(@Valid @RequestBody CategoryRequest request) {
        adminCategoryService.save(request);
        return ApiResponse.success();
    }
}
