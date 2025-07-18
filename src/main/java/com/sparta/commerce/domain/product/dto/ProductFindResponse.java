package com.sparta.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFindResponse {
  private final Long id;
  private final String name;
  private final BigDecimal price;
  private final Integer stock;

  @QueryProjection
  public ProductFindResponse(Long id, String name, BigDecimal price, Integer stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
}
