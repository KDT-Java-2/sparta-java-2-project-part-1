package com.sparta.commerce.domain.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CategoryCreateRequest {

  @NotNull
  String name;
  
  String description;

  Long parentId;
}
