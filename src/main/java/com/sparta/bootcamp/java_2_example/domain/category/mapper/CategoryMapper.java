package com.sparta.bootcamp.java_2_example.domain.category.mapper;

import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryResponse;
import com.sparta.bootcamp.java_2_example.domain.category.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryCreateRequest request);

    CategoryCreateResponse toCreateResponse(Category category);

    CategoryResponse toResponse(Category category);
}
