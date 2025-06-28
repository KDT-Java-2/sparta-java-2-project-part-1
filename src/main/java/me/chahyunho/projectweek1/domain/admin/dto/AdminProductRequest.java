package me.chahyunho.projectweek1.domain.admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductRequest {

  @NotBlank(message = "상품명은 필수입니다.")
  @Size(max = 255, message = "상품명은 255자 이내여야 합니다.")
  private String name;

  @NotBlank(message = "상품 설명은 필수입니다.")
  private String description;

  @NotNull(message = "가격은 필수입니다.")
  @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
  private BigDecimal price;

  @NotNull(message = "재고는 필수입니다.")
  @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
  private Integer stock;

  @NotNull(message = "카테고리 ID는 필수입니다.")
  private Long categoryId;
}
