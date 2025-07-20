package com.sparta.bootcamp.shop.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    CategoryResponse categoryResponse;

    String name;

    String description;

    BigDecimal price;

    Integer stock;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    @QueryProjection
    public ProductResponse(
            Long id,
            CategoryResponse categoryResponse,
            String name,
            String description,
            BigDecimal price,
            Integer stock,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.categoryResponse = categoryResponse;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
    }
}