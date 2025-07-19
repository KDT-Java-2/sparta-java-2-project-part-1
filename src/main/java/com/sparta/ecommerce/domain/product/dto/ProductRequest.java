package com.sparta.ecommerce.domain.product.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

  Long categoryId;

  String name;

  String description;

  @Positive // 값을 양수로만 제한
  BigDecimal minPrice;

  @Positive
  BigDecimal maxPrice;

  int page;
  int size;
  String sortBy;
}
