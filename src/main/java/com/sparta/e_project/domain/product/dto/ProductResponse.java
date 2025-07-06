package com.sparta.e_project.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
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
