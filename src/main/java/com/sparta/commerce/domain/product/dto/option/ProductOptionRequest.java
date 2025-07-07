package com.sparta.commerce.domain.product.dto.option;

import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOptionRequest {
  String key; // 예: 색상
  List<OptionValueAndAddPrice> values; // 예: ["검정", "흰색"]

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @FieldDefaults(level = AccessLevel.PRIVATE)
  public static class OptionValueAndAddPrice {
    String item; // 예: "검정"
    BigDecimal additionalPrice; // 추가 금액
  }
}
