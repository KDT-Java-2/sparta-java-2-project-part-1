package com.sparta.part_1.domain.product.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {


  Long category;
  Integer minPrice;
  Integer maxPrice;
  Integer page = 0;
  Integer size = 10;
  String sortBy; // 이건 그냥 펼쳐서 받거나 Dto를 따로 선언하는게 낫겠는데, 어쨌든 서류에는 String으로 넘긴다고 되어있다.

}
