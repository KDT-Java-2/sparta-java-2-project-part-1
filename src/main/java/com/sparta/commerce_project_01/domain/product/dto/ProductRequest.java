package com.sparta.commerce_project_01.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class ProductRequest {

  @NonNull
  final Long categoryId;

  @NonNull
  final String name;

  final String description;

  final BigDecimal price;
  final Integer stock;
  final String image;
}
