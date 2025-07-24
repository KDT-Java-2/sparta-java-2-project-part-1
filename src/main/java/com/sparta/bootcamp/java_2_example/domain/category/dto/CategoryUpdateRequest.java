package com.sparta.bootcamp.java_2_example.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {

  @NotBlank
  String name;

  String description;

  Long parentId;

}
