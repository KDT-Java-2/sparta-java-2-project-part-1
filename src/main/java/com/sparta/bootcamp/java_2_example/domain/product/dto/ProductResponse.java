package com.sparta.bootcamp.java_2_example.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    String name;

    String description;

    BigDecimal price;

    Integer stock;

    ProductCategoryResponse category;

    public ProductResponse(
            Long id,
            String name,
            String description,
            BigDecimal price,
            Integer stock,
            ProductCategoryResponse category
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

}
