package com.moveuk.ecommerce.application.category.response;

import com.moveuk.ecommerce.domain.category.Category;

import java.util.List;

public class CategoryResult {
    public record CategoryTreeDto(Long id, String name, List<CategoryTreeDto> children) {
        public static CategoryTreeDto from(Category category) {
            return new CategoryTreeDto(
                    category.getId(),
                    category.getName(),
                    category.getChildren().stream()
                            .map(CategoryTreeDto::from)
                            .toList()
            );
        }
    }
}
