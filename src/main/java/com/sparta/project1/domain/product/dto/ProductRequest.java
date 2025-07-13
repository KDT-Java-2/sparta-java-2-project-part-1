package com.sparta.project1.domain.product.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long categoryId;

    @PositiveOrZero
    Integer minPrice;

    @PositiveOrZero
    Integer maxPrice;

    Integer page = 0;

    Integer size = 10;

    String sortBy;
}
