package com.sparta.commerce.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
  @NotNull
  String name;

  @NotNull
  String description;

  @NotNull
  @JsonProperty(defaultValue = "0")
  @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
  BigDecimal price;

  @NotNull
  @JsonProperty(defaultValue = "0")
  @Min(value = 0, message = "수량은 0 이상이어야 합니다.")
  Integer stock;

  String imageUrl;

  @NotNull
  Long categoryId;
}
