package com.sparta.coupang_commerce.domain.category.mapper;

import com.sparta.coupang_commerce.domain.category.dto.CategoryResponse;
import com.sparta.coupang_commerce.domain.category.entity.Category;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  @Mapping(target = "children", source = "children")
  CategoryResponse toCategoryHierarchyResponse(Category category, List<CategoryResponse> children);
}
