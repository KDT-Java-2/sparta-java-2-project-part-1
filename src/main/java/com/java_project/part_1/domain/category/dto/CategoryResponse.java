package com.java_project.part_1.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  Long id;

  String name;

  @Builder.Default // 없으면 초기화 하지 않고 null 둠.
  List<CategoryResponse> children = new ArrayList<>();
}
