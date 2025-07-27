package com.scb.masterCourse.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.scb.masterCourse.commerce.common.enums.ProductStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    String name;

    ProductBrandResponse brand;

    ProductCategoryResponse category;

    String description;

    Integer price;

    Integer stock;

    String status;

    @QueryProjection
    public ProductResponse(
        Long id,
        String name,
        ProductBrandResponse brand,
        ProductCategoryResponse category,
        String description,
        Integer price,
        Integer stock,
        ProductStatus status
    ) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status.toString();
    }
}
