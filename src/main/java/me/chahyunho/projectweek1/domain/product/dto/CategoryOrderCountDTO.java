package me.chahyunho.projectweek1.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryOrderCountDTO {

  final String categoryName;
  final Long orderCount;

  @QueryProjection
  public CategoryOrderCountDTO(String categoryName, Long orderCount) {
    this.categoryName = categoryName;
    this.orderCount = orderCount;
  }
}
