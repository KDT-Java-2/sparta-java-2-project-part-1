package com.sparta.bootcamp.java_2_example.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCategoryResponse {

  Long id;

  String name;

  public ProductCategoryResponse(
          Long id,
          String name
  ) {
    this.id = id;
    this.name = name;
  }

}
