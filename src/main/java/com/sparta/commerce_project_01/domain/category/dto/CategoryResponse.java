package com.sparta.commerce_project_01.domain.category.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  final Long id;

  final String name;

  final String description;

  final Long parentId;

  final Integer depth;

  final List<CategoryResponse> children;

  @Builder
  public CategoryResponse(Long id, String name, String description, Long parentId, Integer depth,
      List<CategoryResponse> categories) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.parentId = parentId;
    this.depth = depth;
    this.children = categories;
  }
}

