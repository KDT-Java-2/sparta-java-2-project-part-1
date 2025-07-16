package com.scb.masterCourse.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    String name;

    ProductBrandResponse brand;

    ProductCategoryResponse category;

    String description;

    Integer price;

    Integer stock;

    @QueryProjection
    public ProductResponse(
        Long id,
        String name,
        ProductBrandResponse brand,
        ProductCategoryResponse category,
        String description,
        Integer price,
        Integer stock
    ) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
