package com.sparta.java2.project.part1.commerce.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategorySearchHierarchyResponse {
    Long id;
    String name;
    List<CategorySearchHierarchyResponse> children = new ArrayList<>();

    public CategorySearchHierarchyResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addChild(CategorySearchHierarchyResponse child) {
        this.children.add(child);
    }
}
