package com.socialcommerce.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
  @NotBlank
  String name;
  @NotBlank
  String description;
  @NotNull
  @PositiveOrZero
  BigDecimal price;
  @NotNull
  @PositiveOrZero
  Integer stock;
  @NotNull
  Long categoryId;
}
