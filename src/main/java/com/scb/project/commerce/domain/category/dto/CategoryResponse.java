package com.scb.project.commerce.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    // 카테고리 아이디
    final Long id;

    // 카테고리명
    final String name;

    // 자식 카테고리 정보
    final List<CategoryResponse> children;

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }
}
