package com.dogworld.dogdog.api.category.response;

import com.dogworld.dogdog.domain.category.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryCreateResponse {

  private Long id;
  private String name;

  public static CategoryCreateResponse from(Category savedCategory) {
    return CategoryCreateResponse.builder()
        .id(savedCategory.getId())
        .name(savedCategory.getName())
        .build();
  }
}
