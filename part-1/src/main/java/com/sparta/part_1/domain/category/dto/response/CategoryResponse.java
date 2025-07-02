package com.sparta.part_1.domain.category.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  final Long id;
  final String name;
  final List<CategoryResponse> children;

  @QueryProjection
  public CategoryResponse(Long id, String name, List<CategoryResponse> children) {
    this.children = children;
    this.name = name;
    this.id = id;
  }
}
