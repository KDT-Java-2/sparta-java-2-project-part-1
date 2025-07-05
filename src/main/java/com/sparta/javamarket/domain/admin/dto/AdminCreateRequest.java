package com.sparta.javamarket.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCreateRequest {
  @NotNull(message = "상품명은 필수 입력 항목입니다.")
  String name;

  @NotNull(message = "상품 설명은 필수 입력 항목입니다.")
  String description;

  @NotNull(message = "가격은 필수 입력 항목입니다.")
  @PositiveOrZero(message = "가격은 양수여야 합니다.")
  Integer price;

  @NotNull(message = "재고는 필수 입력 항목입니다.")
  @PositiveOrZero(message = "가격은 양수여야 합니다.")
  Integer stock;

  @NotNull(message = "카테고리는 필수 입력 항목입니다.")
  Long categoryId;

}
