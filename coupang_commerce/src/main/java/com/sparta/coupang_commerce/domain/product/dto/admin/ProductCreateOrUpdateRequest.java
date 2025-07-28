package com.sparta.coupang_commerce.domain.product.dto.admin;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateOrUpdateRequest {

  @NotNull
  String name;

  @NotNull
  String description;

  @NotNull
  @Min(0)
  BigDecimal price;

  @NotNull
  @Min(0)
  Integer stock;

  @NotNull
  Long categoryId;
}
