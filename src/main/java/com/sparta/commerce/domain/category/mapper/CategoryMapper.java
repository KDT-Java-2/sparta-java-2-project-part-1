package com.sparta.commerce.domain.category.mapper;

import com.sparta.commerce.domain.category.dto.admin.CategoryCreateRequest;
import com.sparta.commerce.domain.category.dto.admin.CategoryResponse;
import com.sparta.commerce.domain.category.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  
  CategoryResponse toResponse(Category category);

}
