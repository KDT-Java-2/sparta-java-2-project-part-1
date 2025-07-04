package com.dogworld.dogdog.category.infrastructure.dto;

public interface FlatCategoryDto {
  Long getId();
  String getName();
  Long getParentId();
  int getDepth();
  int getSortOrder();
  boolean getActive();
}
