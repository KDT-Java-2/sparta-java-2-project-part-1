package com.dogworld.dogdog.category.application;

import com.dogworld.dogdog.category.domain.Category;
import com.dogworld.dogdog.category.domain.repository.CategoryRepository;
import com.dogworld.dogdog.category.infrastructure.dto.FlatCategoryDto;
import com.dogworld.dogdog.category.interfaces.dto.response.CategoryResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryResponse> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    return categories.stream()
        .map(CategoryResponse::from)
        .collect(Collectors.toList());
  }

  public List<CategoryResponse> getAllCategoriesHierarchy() {
    List<FlatCategoryDto> flatList = categoryRepository.findAllFlat();

    Map<Long, CategoryResponse> map = buildCategoryMap(flatList);
    List<CategoryResponse> roots = buildCategoryTree(flatList, map);
    sortTree(roots);

    return roots;
  }

  private Map<Long, CategoryResponse> buildCategoryMap(List<FlatCategoryDto> flatList) {
    Map<Long, CategoryResponse> map = new HashMap<>();

    for(FlatCategoryDto flatCategoryDto : flatList) {
      CategoryResponse response = CategoryResponse.from(flatCategoryDto);
      map.put(flatCategoryDto.getId(), response);
    }
    return map;
  }

  private static List<CategoryResponse> buildCategoryTree(List<FlatCategoryDto> flatList,
      Map<Long, CategoryResponse> map) {
    List<CategoryResponse> roots = new ArrayList<>();

    for(FlatCategoryDto dto : flatList) {
      CategoryResponse child = map.get(dto.getId());
      Long parentId = dto.getParentId();

      if(parentId == null) {
        roots.add(child);
      } else {
        CategoryResponse parent = map.get(parentId);
        if(parent != null) {
          parent.getChildren().add(child);
        }
      }
    }
    return roots;
  }

  private void sortTree(List<CategoryResponse> nodes) {
    nodes.sort(Comparator.comparingInt(CategoryResponse::getSortOrder));
    for(CategoryResponse node : nodes) {
      sortTree(node.getChildren());
    }
  }
}
