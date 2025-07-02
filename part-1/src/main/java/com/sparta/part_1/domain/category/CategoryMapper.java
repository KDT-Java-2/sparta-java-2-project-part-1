package com.sparta.part_1.domain.category;

import com.sparta.part_1.domain.category.dto.response.CategoryResponse;
import com.sparta.part_1.domain.category.entity.Category;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryResponse toDto(Category category);

  List<CategoryResponse> listToDto(List<Category> categoryList);

}
