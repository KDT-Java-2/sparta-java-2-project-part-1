package com.sparta.javamarket.domain.category.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
  private Long id;
  private String name;
  private List<CategoryResponse> children;

  public void addChild(CategoryResponse child) {
    this.children.add(child);
  }
}
