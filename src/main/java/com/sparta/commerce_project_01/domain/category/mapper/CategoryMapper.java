package com.sparta.commerce_project_01.domain.category.mapper;

import com.sparta.commerce_project_01.domain.category.dto.CategoryResponse;
import com.sparta.commerce_project_01.domain.category.entity.Category;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  // Category 엔티티를 CategoryTreeResponse DTO로 매핑하는 메서드
  // 여기서 parentId 필드를 매핑할 때, category.parent가 null인 경우를 처리합니다.
  @Mapping(target = "parentId", expression = "java(category.getParent() != null ? category.getParent().getId() : null)")
  @Mapping(target = "categories", expression = "java(new java.util.ArrayList<>())")
  // children 필드 초기화
  CategoryResponse toCategoryResponse(Category category);

  // List<Category>를 List<CategoryTreeResponse>로 변환하는 메서드
  List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}