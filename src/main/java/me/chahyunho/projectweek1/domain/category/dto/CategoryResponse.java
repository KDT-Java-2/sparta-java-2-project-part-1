package me.chahyunho.projectweek1.domain.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  Long id;

  String name;

  @Builder
  public CategoryResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
