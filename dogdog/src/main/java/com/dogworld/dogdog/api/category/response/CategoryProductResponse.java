package com.dogworld.dogdog.api.category.response;

import com.dogworld.dogdog.domain.category.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryProductResponse {

  private Long id;
  private String name;

  public static CategoryProductResponse from(Category category) {
    return CategoryProductResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }
}
