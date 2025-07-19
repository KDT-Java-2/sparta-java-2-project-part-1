package com.sparta.ecommerce.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {

  Long categoryId;
  String name;
  String description;
  BigDecimal minPrice;
  BigDecimal maxPrice;
}
