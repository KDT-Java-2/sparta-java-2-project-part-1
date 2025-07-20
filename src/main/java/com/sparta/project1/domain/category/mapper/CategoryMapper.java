package com.sparta.project1.domain.category.mapper;

import com.sparta.project1.domain.category.dto.CategoryResponse;
import com.sparta.project1.domain.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "id", target = "categoryId")
    CategoryResponse toResponse(Category category);
}