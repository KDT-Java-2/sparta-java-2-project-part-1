package com.sparta.commerce.domain.category.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryHierarchyResponse {

  Long id;

  String name;

  List<CategoryHierarchyResponse> children;

}
