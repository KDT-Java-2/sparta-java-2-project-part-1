package com.java_project.part_1.domain.product.dto;

import com.java_project.part_1.common.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
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
  @Positive(message = "양수만 입력 가능합니다.")
  BigDecimal price;

  @NotNull(message = "재고는 필수입니다.")
  @Positive(message = "양수만 입력 가능합니다.")
  Integer stock;

  @NotNull
  Long categoryId;

  ProductStatus status;
}
