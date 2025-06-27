package com.sparta.java2.project.part1.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryProductDto {
    final String categoryName;
    final String productName;
    final BigDecimal price;
    final Integer stock;

    @QueryProjection
    public CategoryProductDto(String categoryName, String productName, BigDecimal price, Integer stock) {
        this.categoryName = categoryName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }
}
