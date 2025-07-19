package com.sparta.ecommerce.domain.category.service;


import com.sparta.ecommerce.domain.category.dto.CategoryResponse;
import com.sparta.ecommerce.domain.category.entity.Category;
import com.sparta.ecommerce.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryResponse> getCategoryTree() {
    List<Category> allCategories = categoryRepository.findAll();

    // ID로 카테고리 매핑
    Map<Long, CategoryResponse> map = new HashMap<>();
    List<CategoryResponse> roots = new ArrayList<>();

    for (Category category : allCategories) {
      CategoryResponse dto = new CategoryResponse(category.getId(), category.getCategoryNm());
      map.put(dto.getId(), dto);
    }

    for (Category category : allCategories) {
      CategoryResponse dto = map.get(category.getId());
      if (category.getParent() != null) {
        CategoryResponse parentDto = map.get(category.getParent().getId());
        parentDto.getChildren().add(dto);
      } else {
        roots.add(dto); // 최상위 노드
      }
    }

    return roots;
  }
}
