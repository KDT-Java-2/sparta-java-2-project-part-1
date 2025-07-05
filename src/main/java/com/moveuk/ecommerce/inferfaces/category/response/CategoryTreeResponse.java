package com.moveuk.ecommerce.inferfaces.category.response;

import com.moveuk.ecommerce.application.category.response.CategoryResult;

import java.util.List;

public record CategoryTreeResponse(Long id, String name, List<CategoryTreeResponse> children) {
    public static CategoryTreeResponse from(CategoryResult.CategoryTreeDto category) {
        return new CategoryTreeResponse(
                category.id(),
                category.name(),
                category.children().stream()
                        .map(CategoryTreeResponse::from)
                        .toList()
        );
    }
    public static List<CategoryTreeResponse> from(List<CategoryResult.CategoryTreeDto> dtos) {
        return dtos.stream()
                .map(CategoryTreeResponse::from)
                .toList();
    }
}
