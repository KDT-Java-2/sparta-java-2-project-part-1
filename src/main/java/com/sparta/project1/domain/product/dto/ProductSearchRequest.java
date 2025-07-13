package com.sparta.project1.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {
    Long categoryId;

    Integer minPrice;

    Integer maxPrice;

    Integer page;

    Integer size;

    String sortBy;
}
