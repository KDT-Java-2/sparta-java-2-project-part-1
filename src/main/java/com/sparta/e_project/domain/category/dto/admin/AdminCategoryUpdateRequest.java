package com.sparta.e_project.domain.category.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryUpdateRequest {

  @NotNull
  String name;
  String description;
  Long parentId;
}
