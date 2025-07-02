package com.dogworld.dogdog.api.category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CategoryRequest {

  @NotBlank
  private String name;

  @Positive
  private Long parentId;

  @Positive
  private Integer sortOrder;

  private Boolean active;

}
