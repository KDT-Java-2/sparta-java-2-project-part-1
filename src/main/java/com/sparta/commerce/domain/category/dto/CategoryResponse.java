package com.sparta.commerce.domain.category.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor // 생성자랑 mapStruct의 동작이랑 연관있는거같음.
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
  Long id;
  String name;
  Long parentId;
}
