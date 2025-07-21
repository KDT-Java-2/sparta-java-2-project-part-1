package com.sparta.e_project.domain.category.dto.admin;

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
  String description;
  Long parentId;
}
