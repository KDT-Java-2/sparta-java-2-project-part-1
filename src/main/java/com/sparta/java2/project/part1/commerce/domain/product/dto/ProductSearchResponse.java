package com.sparta.java2.project.part1.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {
    /*
  "message": {
    "content": [
      {
        "id": 10, "name": "스마트 워치", "price": 250000, "stock": 50
      }
    ],
     */
    Long id;

    String name;

    BigDecimal price;

    Integer stock;

    @QueryProjection
    public ProductSearchResponse(Long id, String name, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
