package com.sparta.java_02.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CategoryProductDTO {
    private final String categoryName;
    private final String productName;
    private final Double price;
    private final Integer stock;

    @QueryProjection
    public CategoryProductDTO(String categoryName, String productName, Double price, Integer stock) {
        this.categoryName = categoryName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }
}