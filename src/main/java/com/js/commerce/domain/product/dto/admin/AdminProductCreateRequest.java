package com.js.commerce.domain.product.dto.admin;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductCreateRequest {

  @NotBlank(message = "상품명은 필수입니다.")
  String name;

  @NotBlank(message = "상품 설명은 필수입니다.")
  String description;

  @NotNull(message = "상품 가격은 필수입니다.")
  @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
  Integer price;

  @NotNull(message = "재고 수량은 필수입니다.")
  @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
  Integer stock;

  @NotNull(message = "카테고리 ID는 필수입니다.")
  Long categoryId;

}
