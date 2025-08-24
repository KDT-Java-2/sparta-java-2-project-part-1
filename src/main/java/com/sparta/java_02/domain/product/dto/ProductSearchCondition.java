package com.sparta.java_02.domain.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductSearchCondition {

    private String productName;
    private String categoryName;
    private Integer minPrice;
    private Integer maxPrice;
}