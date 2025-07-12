package com.sparta.commerce.domain.category.dto;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CategoryUpdateRequest {

  String name;

  String description;

  Long parentId;
}
