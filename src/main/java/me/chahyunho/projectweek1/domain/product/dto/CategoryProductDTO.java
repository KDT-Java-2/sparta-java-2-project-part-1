package me.chahyunho.projectweek1.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryProductDTO {

  final String categoryName;
  final String productName;
  final BigDecimal price;
  final Integer stock;

  @QueryProjection
  public CategoryProductDTO(String categoryName, String productName, BigDecimal price,
      Integer stock) {
    this.categoryName = categoryName;
    this.productName = productName;
    this.price = price;
    this.stock = stock;
  }
}
