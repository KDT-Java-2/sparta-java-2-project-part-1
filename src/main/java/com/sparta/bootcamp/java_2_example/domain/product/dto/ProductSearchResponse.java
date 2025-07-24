package com.sparta.bootcamp.java_2_example.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {

  Long id;

  String name;

  BigDecimal price;

  Integer stock;

  @QueryProjection
  public ProductSearchResponse(
      Long id,
      String name,
      BigDecimal price,
      Integer stock
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
}
