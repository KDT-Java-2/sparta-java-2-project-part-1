package com.sparta.javamarket.domain.admin.dto;

import com.sparta.javamarket.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryUpdateResponse {

  Long id;

  String name;

  Category parent;
}
