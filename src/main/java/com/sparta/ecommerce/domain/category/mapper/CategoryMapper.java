package com.sparta.ecommerce.domain.category.mapper;

import com.sparta.ecommerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.ecommerce.domain.category.dto.CategoryCreateResponse;
import com.sparta.ecommerce.domain.category.dto.CategoryInfoResponse;
import com.sparta.ecommerce.domain.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  @Mapping(source = "request.description", target = "description")
  @Mapping(source = "request.name", target = "categoryNm")
  @Mapping(source = "parentCategory", target = "parent")
  Category toEntity(CategoryCreateRequest request, Category parentCategory);

  @Mapping(source = "categoryNm", target = "name")
  @Mapping(source = "parent.id", target = "parentId")
  CategoryInfoResponse toCategoryResponse(Category category);

  @Mapping(source = "id", target = "categoryId")
  CategoryCreateResponse toCategoryCreateResponse(Category category);
}