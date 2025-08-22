package com.sparta.java_02.domain.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequestDto {

  @NotBlank(message = "상품명은 필수 입력 값입니다.")
  private String name;

  private String description;

  @NotNull(message = "가격은 필수 입력 값입니다.")
  @DecimalMin(value = "0.0", message = "가격은 0 이상이어야 합니다.")
  private BigDecimal price;

  @NotNull(message = "재고는 필수 입력 값입니다.")
  @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
  private Integer stock;

  @NotNull(message = "카테고리 ID는 필수 입력 값입니다.")
  private Long categoryId;
}