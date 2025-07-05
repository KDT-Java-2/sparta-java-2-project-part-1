package com.js.commerce.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {

  Long id;

  String name;

  String description;

  BigDecimal price;

  Integer stock;

  CategoryDto category;

}
