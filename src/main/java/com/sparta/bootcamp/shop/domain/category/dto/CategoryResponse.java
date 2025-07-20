package com.sparta.bootcamp.shop.domain.category.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.sparta.bootcamp.shop.domain.category.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    private Long id;
    private String name;
    private List<CategoryResponse> children;

    public static CategoryResponse fromEntity(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .children(new ArrayList<>())
                .build();
    }

    @QueryProjection
    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
