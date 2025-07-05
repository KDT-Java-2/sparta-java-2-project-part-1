package com.dogworld.dogdog.product.interfaces.dto.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ProductSearchCondition {
  private Long category;
  private Integer minPrice;
  private Integer maxPrice;

  @QueryProjection
  public ProductSearchCondition(Long category, Integer minPrice, Integer maxPrice) {
    this.category = category;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
  }
}
