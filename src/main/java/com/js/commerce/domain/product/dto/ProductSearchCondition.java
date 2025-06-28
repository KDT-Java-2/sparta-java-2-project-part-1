package com.js.commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor  // 전부 값이 들어오는 게 아니니까 NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchCondition {

  Long categoryId;

  Integer minPrice;

  Integer maxPrice;

  Integer page = 0;

  Integer size = 10;

  String sortBy;

}
