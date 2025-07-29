package com.sparta.commerce_project_01.domain.category.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  final Long id;

  final String name;

  final String description;

  final Long parentId;

  final List<CategoryResponse> categories;

  @Builder
  public CategoryResponse(Long id, String name, String description, Long parentId,
      List<CategoryResponse> categories) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.parentId = parentId;
    this.categories = categories;
  }

}

