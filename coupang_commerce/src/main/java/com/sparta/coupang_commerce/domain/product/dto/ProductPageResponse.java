package com.sparta.coupang_commerce.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

//@Setter
//@NoArgsConstructor     // for Jackson
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPageResponse {

  Long id;

  String name;

  BigDecimal price;

  Integer stock;
}
