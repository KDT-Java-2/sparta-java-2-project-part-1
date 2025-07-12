package com.sparta.commerce.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
  Long id;
  String name;
  CategoryResponse category;
  String description;
  BigDecimal price;
  Integer stock;

  @Builder
  public ProductDetailResponse(Long id, String name, CategoryResponse category, String description,
      BigDecimal price, Integer stock) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.description = description;
    this.price = price;
    this.stock = stock;
  }
}
