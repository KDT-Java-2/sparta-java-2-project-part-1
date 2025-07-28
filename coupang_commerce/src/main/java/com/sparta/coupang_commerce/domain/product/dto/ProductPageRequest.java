package com.sparta.coupang_commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPageRequest {

  Long categoryId;

  Integer minPrice;

  Integer maxPrice;

}
