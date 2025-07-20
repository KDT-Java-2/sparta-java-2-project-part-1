package com.example.shoppingmall.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryHierarchyResponse {
    Long id;
    String name;
    List<CategoryHierarchyResponse> children = new ArrayList<>();

    public CategoryHierarchyResponse(Long id, String name, List<CategoryHierarchyResponse> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public List<CategoryHierarchyResponse> getChildren() {
        return children;
    }
}