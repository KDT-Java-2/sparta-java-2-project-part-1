package com.sparta.commerce_project_01.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
  Long categoryId;

  @NonNull
  String name;

  String description;

  @NotNull
  @Positive // 값을 양수로만 제한
  BigDecimal price;

  @NotNull
  @PositiveOrZero // 갑을 양수 또는 0으로 제한
  Integer stock;

  String image;
}
