package com.sparta.commerce_project_01.domain.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CategoryProductDTO {

  private final String categoryName;
  private final String productName;
  private final BigDecimal price;
  private final Integer stock;

  @QueryProjection
  public CategoryProductDTO(String categoryName, String productName, BigDecimal price,
      Integer stock) {
    this.categoryName = categoryName;
    this.productName = productName;
    this.price = price;
    this.stock = stock;
  }

}
