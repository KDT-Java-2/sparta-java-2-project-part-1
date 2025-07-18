package com.sparta.commerce.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateResponse {
  Long productId;
  String name;
  String description;
  Integer stock;
  String imageUrl;
  BigDecimal price;
  Long categoryId;

  @Builder
  public ProductUpdateResponse(Long productId, String name, String description, Integer stock, String imageUrl, BigDecimal price, Long categoryId) {
    this.productId = productId;
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.price = price;
    this.categoryId = categoryId;
  }
}
