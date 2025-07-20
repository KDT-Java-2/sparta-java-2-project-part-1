package com.sparta.ecommerce.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

  @NotNull
  Long categoryId;

  @NotNull
  String name;

  @NotNull
  String description;

  @Positive
  BigDecimal price;

  @Positive
  Integer stock;

  String imageUrl;

  Boolean isActive;
}
