package com.sparta.commerce.domain.category.service;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.category.dto.CategoryHierarchyResponse;
import com.sparta.commerce.domain.category.dto.admin.CategoryCreateRequest;
import com.sparta.commerce.domain.category.dto.admin.CategoryResponse;
import com.sparta.commerce.domain.category.dto.admin.CategoryUpdateRequest;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.mapper.CategoryMapper;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Transactional(readOnly = true)
  public List<CategoryHierarchyResponse> findAllCategories() {
    List<Category> allCategories = categoryRepository.findAll();

    Map<Long, CategoryHierarchyResponse> categoryMap = new HashMap<>();
    List<CategoryHierarchyResponse> rootCategories = new ArrayList<>();

    for (Category category : allCategories) {
      CategoryHierarchyResponse categoryList = CategoryHierarchyResponse.builder()
          .id(category.getId())
          .name(category.getName())
          .children(new ArrayList<>())
          .build();
      categoryMap.put(category.getId(), categoryList);
    }

    for (Category category : allCategories) {
      CategoryHierarchyResponse currentCategory = categoryMap.get(category.getId());

      if (category.getParent() == null) {
        rootCategories.add(currentCategory);
      } else {
        CategoryHierarchyResponse parentCategory = categoryMap.get(category.getParent().getId());
        if (parentCategory != null) {
          parentCategory.getChildren().add(currentCategory);
        }
      }
    }
    return rootCategories;
  }

  @Transactional
  public CategoryResponse createCategory(CategoryCreateRequest request) {
    Category parentCategory = null;
    if (request.getParentId() != null) {
      parentCategory = findCategoryId(request.getParentId());
    }

    Category category = Category.builder()
        .name(request.getName())
        .parent(parentCategory)
        .description(request.getDescription())
        .build();

    return categoryMapper.toResponse(categoryRepository.save(category));
  }

  @Transactional
  public CategoryResponse updateCategory(Long categoryId, CategoryUpdateRequest request) {
    Category category = findCategoryId(categoryId);

    if (request.getParentId() != null && categoryId.equals(request.getParentId())) {
      throw new ServiceException(ServiceExceptionCode.INVALID_PARENT_CATEGORY);
    }

    category.setName(request.getName());

    if (request.getParentId() != null) {
      Category parentCategory = findCategoryId(request.getParentId());
      category.setParent(parentCategory);
    } else {
      category.setParent(null);
    }

    category.setDescription(request.getDescription());

    return categoryMapper.toResponse(categoryRepository.save(category));
  }

  @Transactional
  public void deleteCategory(Long categoryId) {
    findCategoryId(categoryId);
    categoryRepository.deleteById(categoryId);
  }

  private Category findCategoryId(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
  }

}
