package org.example.prj_part_1.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {
  Long id;
  String name;
  BigDecimal price;
  Integer stock;

  @QueryProjection
  public ProductSearchResponse(Long id, String name, BigDecimal price, Integer stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
}
