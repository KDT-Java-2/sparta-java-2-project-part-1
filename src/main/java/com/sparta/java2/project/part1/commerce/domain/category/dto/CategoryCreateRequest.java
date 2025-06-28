package com.sparta.java2.project.part1.commerce.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateRequest {
    @NotBlank
    String name;
    String description;
    Long parentId;
}
