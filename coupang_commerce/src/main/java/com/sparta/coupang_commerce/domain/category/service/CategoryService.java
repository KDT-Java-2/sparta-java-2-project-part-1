package com.sparta.coupang_commerce.domain.category.service;

import com.sparta.coupang_commerce.common.exception.ServiceException;
import com.sparta.coupang_commerce.common.exception.ServiceExceptionCode;
import com.sparta.coupang_commerce.domain.category.dto.CategoryResponse;
import com.sparta.coupang_commerce.domain.category.entity.Category;
import com.sparta.coupang_commerce.domain.category.mapper.CategoryMapper;
import com.sparta.coupang_commerce.domain.category.repository.CategoryRepository;
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
  private final CategoryMapper categoryMapper;

  public List<CategoryResponse> getCategoryHierarchy() {
    List<Category> categories = categoryRepository.findAll();
    return generateCategoryHierarchy(categories);
  }

  private List<CategoryResponse> generateCategoryHierarchy(List<Category> categories) {
    Map<Long, CategoryResponse> categoryResponseMap = new HashMap<>();

    for (Category category : categories) {
      CategoryResponse categoryResponse = categoryMapper.toCategoryHierarchyResponse(category, new ArrayList<>());
      categoryResponseMap.put(category.getId(), categoryResponse);
    }

    List<CategoryResponse> categoriesHierarchy = new ArrayList<>();
    for (Category category : categories) {
      CategoryResponse childCategory = categoryResponseMap.get(category.getId());
      if (category.getParent() == null) {
        categoriesHierarchy.add(childCategory);
      } else {
        CategoryResponse parentCategory = categoryResponseMap.get(category.getParent().getId());
        if (parentCategory != null) {
          parentCategory.getChildren().add(childCategory);
        } else {
          throw new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA);
        }
      }
    }
    return categoriesHierarchy;
  }
}
