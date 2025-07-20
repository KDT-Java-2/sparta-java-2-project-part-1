package com.example.shoppingmall.domain.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListResponse {
    Long id;
    String name;
    BigDecimal price;
    Integer stock;
    String imageUrl;
    Long categoryId;
    String categoryName;
    LocalDateTime createdAt;

    @QueryProjection
    public ProductListResponse(Long id, String name, BigDecimal price, Integer stock, String imageUrl, Long categoryId, String categoryName, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
    }
} 