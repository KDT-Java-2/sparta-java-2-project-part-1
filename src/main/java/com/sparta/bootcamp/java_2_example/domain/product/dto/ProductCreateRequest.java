package com.sparta.bootcamp.java_2_example.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

  @NotBlank
  String name;

  @NotBlank
  String description;

  @NotNull
  @PositiveOrZero
  Integer price;

  @NotNull
  @PositiveOrZero
  Integer stock;

  @NotNull
  Long categoryId;

}
