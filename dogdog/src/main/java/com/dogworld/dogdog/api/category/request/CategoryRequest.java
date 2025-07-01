package com.dogworld.dogdog.api.category.request;

import lombok.Getter;

@Getter
public class CategoryRequest {

  private String name;
  private Long parentId;
  private Integer sortOrder;
  private Boolean active;

}
