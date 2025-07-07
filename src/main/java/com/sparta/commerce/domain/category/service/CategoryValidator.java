package com.sparta.commerce.domain.category.service;

import static com.sparta.commerce.common.exception.ServiceExceptionCode.CATEGORY_HAS_CHILDREN;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.CATEGORY_HAS_PRODUCTS;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.CATEGORY_SELF_PARENT;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.NOT_FOUND_CATEGORY;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.NOT_FOUND_DATA;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.domain.category.dto.CategoryRequest;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryValidator {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public Category getCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(
        () -> new ServiceException(NOT_FOUND_CATEGORY)
    );
    return category;
  }

  public void validateParentCategoryNotSelf(Long categoryId, CategoryRequest request) {
    // 자기 자신을 부모로 지정하는 케이스 방지
    if (request.getParentId() != null && request.getParentId().equals(categoryId)) {
      throw new ServiceException(CATEGORY_SELF_PARENT);
    }
  }

  public Category validateExistParentCategory(Long parentId){
    Category parent = null;
    if (parentId != null) {
      parent = categoryRepository.findById(parentId)
          .orElseThrow(() -> new ServiceException(NOT_FOUND_DATA));
    }
    return parent;
  }


  public void validateNoProductsInCategory(Category category) {
    boolean hasProducts = productRepository.existsByCategory(category);
    if (hasProducts) {
      throw new ServiceException(CATEGORY_HAS_PRODUCTS);
    }
  }

  public void validateNoChildCategories(Category category) {
    boolean hasChild = categoryRepository.existsByParent(category);
    if (hasChild) {
      throw new ServiceException(CATEGORY_HAS_CHILDREN);
    }
  }

}
