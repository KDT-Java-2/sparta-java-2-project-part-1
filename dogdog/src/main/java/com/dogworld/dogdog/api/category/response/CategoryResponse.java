package com.dogworld.dogdog.api.category.response;

import com.dogworld.dogdog.domain.category.Category;
import com.dogworld.dogdog.domain.category.repository.FlatCategoryDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CategoryResponse {

  private Long id;
  private String name;
  private int depth;
  private int sortOrder;
  private boolean active;
  private List<CategoryResponse> children;

  public static CategoryResponse from(Category category) {
    return CategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .depth(category.getDepth())
        .sortOrder(category.getSortOrder())
        .active(category.getActive())
        .children(new ArrayList<>())
        .build();
  }

  public static CategoryResponse from(FlatCategoryDto dto) {
    return CategoryResponse.builder()
        .id(dto.getId())
        .name(dto.getName())
        .depth(dto.getDepth())
        .sortOrder(dto.getSortOrder())
        .active(dto.getActive())
        .children(new ArrayList<>())
        .build();
  }
}
