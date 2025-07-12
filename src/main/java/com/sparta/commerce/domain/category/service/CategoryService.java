package com.sparta.commerce.domain.category.service;

import com.sparta.commerce.domain.category.dto.CategoryTreeResponse;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.mapper.CategoryMapper;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryTreeResponse> findAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    Map<Long, CategoryTreeResponse> categoryMap = categories.stream()
        .map(categoryMapper::toCategoryTreeResponse) // 엔티티를 DTO로 변환
        .collect(Collectors.toMap(CategoryTreeResponse::getId, dto -> dto));

    List<CategoryTreeResponse> rootCategories = new ArrayList<>(); // 최상위 카테고리들을 담을 리스트

    // 3. 트리 구조를 재조립합니다.
    // 모든 DTO를 순회하면서 부모-자식 관계를 연결합니다.
    for (CategoryTreeResponse categoryDto : categoryMap.values()) {
      if (categoryDto.getParentId() == null || categoryDto.getParentId() == 0) {
        // 부모 ID가 null이면 최상위 카테고리입니다.
        rootCategories.add(categoryDto);
      } else {
        // 부모 ID가 있으면 해당 부모 DTO를 찾아 자식 리스트에 추가합니다.
        CategoryTreeResponse parentDto = categoryMap.get(categoryDto.getParentId());
        if (parentDto != null) {
          // 부모 DTO가 맵에 존재하는 경우에만 자식으로 추가 (데이터 무결성 문제 방지)
          parentDto.getChildren().add(categoryDto);
        }
        // 만약 parentDto가 null이라면, 해당 자식 카테고리는 유효하지 않은 부모 ID를 가지고 있거나
        // 부모 카테고리가 데이터베이스에 없는 경우입니다. (이 경우 해당 자식은 트리에 포함되지 않음)
      }
    }

    // 4. (선택 사항) 트리 구조의 각 레벨에서 카테고리들을 정렬합니다.
    // 예를 들어, 이름 순으로 정렬할 수 있습니다.
    sortCategoryTree(rootCategories);

    return rootCategories;
  }

  private void sortCategoryTree(List<CategoryTreeResponse> categories) {
    if (categories == null || categories.isEmpty()) {
      return;
    }
    // 현재 레벨의 카테고리들을 이름 순으로 정렬
    categories.sort(Comparator.comparing(CategoryTreeResponse::getName));

    // 각 카테고리의 자식 리스트도 재귀적으로 정렬
    for (CategoryTreeResponse category : categories) {
      sortCategoryTree(category.getChildren());
    }
  }
}
