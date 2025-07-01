package com.dogworld.dogdog.domain.category.repository;

public interface FlatCategoryDto {
  Long getId();
  String getName();
  Long getParentId();
  int getDepth();
  int getSortOrder();
  boolean getActive();
}
