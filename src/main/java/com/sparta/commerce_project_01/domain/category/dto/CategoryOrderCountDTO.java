package com.sparta.commerce_project_01.domain.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CategoryOrderCountDTO {

  private final String categoryName;
  private final Long orderCount;

  @QueryProjection
  public CategoryOrderCountDTO(String categoryName, Long orderCount) {
    this.categoryName = categoryName;
    this.orderCount = orderCount;
  }

}
