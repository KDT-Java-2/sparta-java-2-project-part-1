package com.sparta.e_project.domain.product.dto.admin;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class AdminProductRequest {

  @NotNull
  String name;
  @NotNull
  String description;
  @NotNull
  Integer price;
  @NotNull
  Integer stock;
  @NotNull
  Long categoryId;
}
