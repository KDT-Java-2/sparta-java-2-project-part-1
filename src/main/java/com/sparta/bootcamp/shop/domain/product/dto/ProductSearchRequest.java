package com.sparta.bootcamp.shop.domain.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {
    private Long categoryId;
    private Integer minPrice;
    private Integer maxPrice;
}
