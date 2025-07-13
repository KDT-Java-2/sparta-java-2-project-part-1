package com.sparta.commerce.domain.product.dto.admin;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {

  @NotBlank(message = "이름은 필수입니다")
  String name;

  @NotBlank(message = "상품 설명은 필수입니다")
  String description;

  @NotNull(message = "가격은 필수입니다")
  @DecimalMin(value = "0.0", message = "가격은 0 이상이어야 합니다")
  BigDecimal price;

  @NotNull(message = "재고는 필수입니다")
  @Min(value = 0, message = "재고는 0 이상이어야 합니다")
  Integer stock;

  @NotNull(message = "카테고리는 필수입니다")
  Long categoryId;

  @NotNull
  Boolean isActive = true;


}
