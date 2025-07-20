package com.moveuk.ecommerce.application.admin.category.response;

import com.moveuk.ecommerce.domain.category.Category;

import java.util.List;

public class AdminCategoryResult {
    public record CategoryRegisterV1(Long categoryId) {}
    public record CategoryRegisterV2(String name, String description, Long parentId) {}

    public record CategoryTreeDto(Long id, String name, List<AdminCategoryResult.CategoryTreeDto> children) {
        public static AdminCategoryResult.CategoryTreeDto from(Category category) {
            return new AdminCategoryResult.CategoryTreeDto(
                    category.getId(),
                    category.getName(),
                    category.getChildren().stream()
                            .map(AdminCategoryResult.CategoryTreeDto::from)
                            .toList()
            );
        }
    }
}
