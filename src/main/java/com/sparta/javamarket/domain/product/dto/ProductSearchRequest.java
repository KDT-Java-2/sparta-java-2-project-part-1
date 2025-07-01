package com.sparta.javamarket.domain.product.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
public class ProductSearchRequest {

  Long category;
  Integer minPrice;
  Integer maxPrice;
  Integer page;
  Integer size;
  String sortBy;

}
