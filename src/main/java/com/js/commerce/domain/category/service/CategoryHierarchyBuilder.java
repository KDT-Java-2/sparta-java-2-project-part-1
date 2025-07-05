package com.js.commerce.domain.category.service;

import com.js.commerce.domain.category.dto.CategoryHierarchyDto;
import com.js.commerce.domain.category.entity.Category;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CategoryHierarchyBuilder {

  public List<CategoryHierarchyDto> buildTree(List<Category> categories) {
    // 1) ID -> DTO 맵핑
    Map<Long, CategoryHierarchyDto> categoryHierarchyDtoMap = new HashMap<>(categories.size());
    for (Category category : categories) {
      categoryHierarchyDtoMap.put(
          category.getId(),
          new CategoryHierarchyDto(category.getId(), category.getName(), new ArrayList<>())
      );
    }

    // 트리 조립: 최상위는 roots, 나머지는 parent의 children에 추가
    List<CategoryHierarchyDto> roots = new ArrayList<>();
    for (Category category : categories) {
      CategoryHierarchyDto dto = categoryHierarchyDtoMap.get(category.getId());
      if (category.getParent() == null) {
        roots.add(dto);
      } else {
        CategoryHierarchyDto parentDto = categoryHierarchyDtoMap.get(category.getParent().getId());
        if (parentDto != null) {
          parentDto.getChildren().add(dto);
        }
      }
    }
    return roots;
  }
}
