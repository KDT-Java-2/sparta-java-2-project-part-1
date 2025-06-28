package com.sparta.java2.project.part1.commerce.domain.category.dto;

import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateResponse {
    Long categoryId;
    String name;
    String description;
    Category parent;
}
