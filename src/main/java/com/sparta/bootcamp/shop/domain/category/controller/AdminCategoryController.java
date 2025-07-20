package com.sparta.bootcamp.shop.domain.category.controller;

import com.sparta.bootcamp.shop.common.response.ApiResponse;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryRequest;
import com.sparta.bootcamp.shop.domain.category.service.AdminCategoryService;
import com.sparta.bootcamp.shop.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{categoryId}")
    public ApiResponse<Void> edit(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest request) {
        adminCategoryService.edit(categoryId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("{categoryId}")
    public ApiResponse<Void> delete(@PathVariable Long categoryId) {
        adminCategoryService.delete(categoryId);
        return ApiResponse.success();
    }

}
