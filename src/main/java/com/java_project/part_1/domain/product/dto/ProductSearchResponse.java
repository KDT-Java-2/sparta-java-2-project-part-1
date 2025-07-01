package com.java_project.part_1.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {

  Long id;
  
  String name;

  String description;

  BigDecimal price;

  Integer stock;

  Long categoryId;
}
