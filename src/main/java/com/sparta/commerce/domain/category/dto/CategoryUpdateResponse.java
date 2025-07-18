package com.sparta.commerce.domain.category.dto;

import com.sparta.commerce.domain.category.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CategoryUpdateResponse {
  Long id;

  String name;

  String description;

  Category parentId;
}
