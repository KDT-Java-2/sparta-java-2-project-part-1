package com.sparta.coupang_commerce.domain.category.mapper;

import com.sparta.coupang_commerce.domain.category.dto.CategoryResponse;
import com.sparta.coupang_commerce.domain.category.dto.admin.CategoryCreateOrUpdateRequest;
import com.sparta.coupang_commerce.domain.category.dto.admin.CategoryCreateResponse;
import com.sparta.coupang_commerce.domain.category.entity.Category;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  @Mapping(target = "children", source = "children")
  CategoryResponse toCategoryHierarchyResponse(Category category, List<CategoryResponse> children);

  @Mapping(target = "parent", ignore = true)
  Category toCategory(CategoryCreateOrUpdateRequest request);

  @Mapping(target = "categoryId", source = "category.id")
  CategoryCreateResponse toCategoryCreateResponse(Category category);
}
