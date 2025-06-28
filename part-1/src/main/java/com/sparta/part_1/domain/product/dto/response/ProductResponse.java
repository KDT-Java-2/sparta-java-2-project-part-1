package com.sparta.part_1.domain.product.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

  Long id;

  String name;

  BigDecimal price;

  Integer stock;

  @QueryProjection
  public ProductResponse(Long id, String name, BigDecimal price, Integer stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
}
