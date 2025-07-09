package com.sparta.commerce.domain.category.mapper;

import com.sparta.commerce.domain.category.dto.CategoryTreeResponse;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.product.dto.CategoryResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  // Category 엔티티를 CategoryResponse DTO로 매핑하는 메서드 (이전에 만든 것)
  CategoryResponse toCategoryResponse(Category category);

  // Category 엔티티를 CategoryTreeResponse DTO로 매핑하는 메서드
  // 여기서 parentId 필드를 매핑할 때, category.parent가 null인 경우를 처리합니다.
  @Mapping(target = "parentId", expression = "java(category.getParent() != null ? category.getParent().getId() : null)")
  @Mapping(target = "children", expression = "java(new java.util.ArrayList<>())") // children 필드 초기화
  CategoryTreeResponse toCategoryTreeResponse(Category category);

  // List<Category>를 List<CategoryTreeResponse>로 변환하는 메서드
  List<CategoryTreeResponse> toCategoryTreeResponseList(List<Category> categories);
}
