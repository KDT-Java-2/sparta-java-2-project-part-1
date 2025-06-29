package com.java_project.part_1.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {

  @NotBlank
  String name;

  String description;

  Long parentId;
}
