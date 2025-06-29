package com.java_project.part_1.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

  @NotBlank
  @Positive(message = "양수만 입력 가능합니다.")
  Integer price;

  @NotBlank
  @Positive(message = "양수만 입력 가능합니다.")
  Integer stock;

  @NotBlank
  Long categoryId;
}
