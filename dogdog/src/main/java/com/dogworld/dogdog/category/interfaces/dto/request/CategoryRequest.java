package com.dogworld.dogdog.category.interfaces.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CategoryRequest {

  @NotBlank(message = "{validation.category.name.required}")
  private String name;

  @Positive(message = "{validation.category.parentId.positive}")
  private Long parentId;

  @Positive(message = "{validation.category.sortOrder.positive}")
  private Integer sortOrder;

  private Boolean active;

}
