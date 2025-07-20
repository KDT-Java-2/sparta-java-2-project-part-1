package com.sparta.project1.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryHierarchyResponse {

  Long id;

  String name;

  List<CategoryHierarchyResponse> children = new ArrayList<>();

  @Builder
  public CategoryHierarchyResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
