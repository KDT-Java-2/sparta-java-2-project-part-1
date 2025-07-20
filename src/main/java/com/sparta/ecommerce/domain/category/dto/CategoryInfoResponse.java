package com.sparta.ecommerce.domain.category.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryInfoResponse {

  String name;
  String description;
  Long parentId;
}
