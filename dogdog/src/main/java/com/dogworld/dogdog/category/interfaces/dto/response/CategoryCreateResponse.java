package com.dogworld.dogdog.category.interfaces.dto.response;

import com.dogworld.dogdog.category.domain.Category;
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
