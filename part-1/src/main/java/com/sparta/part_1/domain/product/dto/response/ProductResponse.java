package com.sparta.part_1.domain.product.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.part_1.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    String name;

    BigDecimal price;

    Integer stock;

    Category category;

    @QueryProjection
    public ProductResponse(Long id, String name, BigDecimal price, Integer stock, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
