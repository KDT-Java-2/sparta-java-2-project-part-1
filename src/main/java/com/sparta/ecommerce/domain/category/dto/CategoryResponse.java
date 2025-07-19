package com.sparta.ecommerce.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  private Long id;
  private String name;
  private List<CategoryResponse> children = new ArrayList<>();

  public CategoryResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
