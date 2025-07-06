package com.sparta.e_project.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

  Long category;
  Integer minPrice;
  Integer maxPrice;
  Integer page = 0;
  Integer size = 10;
  String sortBy = "createdAt,desc"; // 기본 정렬 기준은 생성일자 내림차순


}
