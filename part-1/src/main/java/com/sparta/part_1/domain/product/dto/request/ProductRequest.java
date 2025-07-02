package com.sparta.part_1.domain.product.dto.request;

import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

  String name;

  @Min(value = 1000, message = "상품 가격은 1000원 이하일 수 없습니다!")
  BigDecimal price;

  @Min(value = 1, message = "상품재고는 1개 이상이어야합니다!")
  Integer stock;

  Long categoryId;

  String description;

}
