package com.sparta.java2.project.part1.commerce.domain.product.dto;

import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateResponse {
    Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    Category category;
}
