package com.sparta.project1.domain.product.dto;

import com.sparta.project1.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {

    Long id;

    String name;

    String description;

    BigDecimal price;

    Integer stock;

    Category category;
}
