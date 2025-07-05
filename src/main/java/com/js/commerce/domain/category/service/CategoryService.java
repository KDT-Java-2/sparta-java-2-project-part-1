package com.js.commerce.domain.category.service;

import com.js.commerce.domain.category.dto.CategoryHierarchyDto;
import com.js.commerce.domain.category.entity.Category;
import com.js.commerce.domain.product.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryHierarchyBuilder hierarchyBuilder;

  @Transactional(readOnly = true)
  public List<CategoryHierarchyDto> getCategoryHierarchy() {
    List<Category> allCategories = categoryRepository.findAll();
    return hierarchyBuilder.buildTree(allCategories);
  }


}
