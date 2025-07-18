package com.sparta.commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // Projections.constructor() 또는 MapStruct가 사용
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
  Long id;
  String name; // 카테고리 이름
}
