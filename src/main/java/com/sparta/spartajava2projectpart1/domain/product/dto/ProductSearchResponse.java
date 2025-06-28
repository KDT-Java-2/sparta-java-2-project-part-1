package com.sparta.spartajava2projectpart1.domain.product.dto;

import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Integer stock;
    Category category;
}
