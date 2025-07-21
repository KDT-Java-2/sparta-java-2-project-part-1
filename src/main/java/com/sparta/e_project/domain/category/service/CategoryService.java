package com.sparta.e_project.domain.category.service;

import com.sparta.e_project.domain.category.dto.CategoryResponse;
import com.sparta.e_project.domain.category.entity.Category;
import com.sparta.e_project.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Transactional
  public List<CategoryResponse> getAllCategoryHierarchy() {
    List<Category> categories = categoryRepository.findAll();

    Map<Long, CategoryResponse> categoryResponseMap = new HashMap<>();

    for (Category category : categories) {
      CategoryResponse response = CategoryResponse.builder()
          .id(category.getId())
          .name(category.getName())
          .children(new ArrayList<>())
          .build();
      categoryResponseMap.put(category.getId(), response);
    }

    List<CategoryResponse> rootCategories = new ArrayList<>();

    for (Category category : categories) {
      CategoryResponse categoryResponse = categoryResponseMap.get(category.getId());
      // 루트 카테고리이면
      if (ObjectUtils.isEmpty(category.getParent())) {
        rootCategories.add(categoryResponse);
      } else { // 루트 카테고리가 아니면
        CategoryResponse parentCategoryResponse = categoryResponseMap.get(
            category.getParent().getId());
        if (!ObjectUtils.isEmpty(parentCategoryResponse)) {
          parentCategoryResponse.getChildren().add(categoryResponse);
        }
      }
    }
    return rootCategories;
  }
}
