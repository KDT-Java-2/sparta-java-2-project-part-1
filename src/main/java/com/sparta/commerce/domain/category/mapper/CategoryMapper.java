package com.sparta.commerce.domain.category.mapper;

import com.sparta.commerce.domain.category.dto.CategoryResponse;
import com.sparta.commerce.domain.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  @Mapping(target = "parentId", source = "parent.id")
  CategoryResponse toResponse(Category category);
}
