package com.sparta.commerce.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryTreeDto {
  private Long id;
  private String name;
  private List<CategoryTreeDto> children;

  public CategoryTreeDto(Long id, String name) {
    this.id = id;
    this.name = name;
    this.children = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<CategoryTreeDto> getChildren() {
    return children;
  }

  public void addChild(CategoryTreeDto child) {
    this.children.add(child);
  }
}
