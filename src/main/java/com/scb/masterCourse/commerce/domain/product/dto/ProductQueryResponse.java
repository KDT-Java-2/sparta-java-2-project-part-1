package com.scb.masterCourse.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductQueryResponse {

    Long id;

    String name;

    String brand;

    String description;

    BigDecimal price;

    Integer stock;

    @QueryProjection
    public ProductQueryResponse(
        Long id,
        String name,
        String brand,
        String description,
        BigDecimal price,
        Integer stock
    ) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
