package com.java_project.part_1.domain.category.service;

import com.java_project.part_1.domain.category.dto.CategoryResponse;
import com.java_project.part_1.domain.category.entity.Category;
import com.java_project.part_1.domain.category.repository.CategoryQueryRepository;
import com.java_project.part_1.domain.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryQueryRepository categoryQueryRepository;

  @Transactional
  public List<CategoryResponse> getCategoryHierarchy() {
    List<Category> categories = categoryQueryRepository.findCategoryHierarchy();

    Map<Long, CategoryResponse> map = new HashMap<>();
    for (Category category : categories) {
      map.put(category.getId(), CategoryResponse.builder()
          .id(category.getId())
          .name(category.getName())
          .build());
    }

    List<CategoryResponse> roots = new ArrayList<>();

    for (Category category : categories) {
      CategoryResponse node = map.get(category.getId());

      if (ObjectUtils.isEmpty(category.getParent())) {
        roots.add(node);
      } else {
        CategoryResponse parent = map.get(category.getParent().getId());
        if (!ObjectUtils.isEmpty(parent)) {
          parent.getChildren().add(node);
        }
      }
    }

    return roots;
  }
}
