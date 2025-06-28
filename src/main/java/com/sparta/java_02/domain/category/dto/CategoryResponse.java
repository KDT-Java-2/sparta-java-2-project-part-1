package com.sparta.java_02.domain.category.dto;

import com.sparta.java_02.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private List<CategoryResponse> children;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getChildren().stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList())
        );
    }

    // 간단한 카테고리 응답 (children 없이)
    public static CategoryResponse simpleFrom(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription(),
            null
        );
    }
} 