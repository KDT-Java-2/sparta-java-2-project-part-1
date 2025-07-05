package com.dogworld.dogdog.category.interfaces.dto.response;

import com.dogworld.dogdog.category.domain.Category;
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
