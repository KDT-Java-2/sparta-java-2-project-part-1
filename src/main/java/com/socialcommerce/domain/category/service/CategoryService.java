package com.socialcommerce.domain.category.service;

import com.socialcommerce.domain.category.dto.CategoryTreeResponse;
import com.socialcommerce.domain.category.repository.CategoryRepository;
import com.socialcommerce.domain.category.entity.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryTreeResponse> getCategoryHierarchy(){
    List<Category> allCategories = categoryRepository.findAll();

    // 1. 최상위 카테고리만 추리기
    List<Category> roots = allCategories.stream()
        .filter(c -> c.getParent() == null)
        .toList();

    // 2. 트리형 DTO 변환(재귀)
    return roots.stream()
        // 람다식으로 쓸 때
        .map(category -> this.toTreeResponse(category))
        // 메서드 레퍼런스 사용
        //.map(this::toTreeResponse)
        .toList();
  }

  private CategoryTreeResponse toTreeResponse(Category category) {
    return CategoryTreeResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .children(
            category.getChildren().stream()
                .map(this::toTreeResponse)
                .toList()
        )
        .build();
  }
}
