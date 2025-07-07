package com.sparta.commerce.domain.product.dto.option;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOptionResponse {
  Long productItemId;
  Integer stock;
  List<ProductOptionDto> options;

  public static ProductOptionResponse of(Long productItemId, Integer stock, List<ProductOptionDto> options) {
    return ProductOptionResponse.builder()
        .productItemId(productItemId)
        .stock(stock)
        .options(options)
        .build();
  }
}
