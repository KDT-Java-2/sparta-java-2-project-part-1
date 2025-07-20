package com.sparta.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {

  private Long id;

  private String name;

  private Long categoryId;

  private BigDecimal price;

  private Integer stock;

  private LocalDateTime createdAt;

  @QueryProjection
  public ProductSearchResponse(Long id, String name, Long categoryId, BigDecimal price, Integer stock, LocalDateTime createdAt) {
    this.id = id;
    this.name = name;
    this.categoryId = categoryId;
    this.price = price;
    this.stock = stock;
    this.createdAt = createdAt;
  }
}
