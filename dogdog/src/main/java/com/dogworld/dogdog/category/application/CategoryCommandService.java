package com.dogworld.dogdog.category.application;

import com.dogworld.dogdog.category.domain.Category;
import com.dogworld.dogdog.category.domain.repository.CategoryRepository;
import com.dogworld.dogdog.category.interfaces.dto.request.CategoryRequest;
import com.dogworld.dogdog.category.interfaces.dto.response.CategoryCreateResponse;
import com.dogworld.dogdog.category.interfaces.dto.response.CategoryResponse;
import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
import com.dogworld.dogdog.product.domain.repository.ProductRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryCommandService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public CategoryCreateResponse createCategory(CategoryRequest request) {

    Category parent = getParentCategory(request.getParentId());

    Category createdCategory = Category.create(request, parent);
    Category savedCategory = categoryRepository.save(createdCategory);
    return CategoryCreateResponse.from(savedCategory);
  }

  public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
    validateNotSelfAsParent(categoryId, request.getParentId());
    Category category = getCategory(categoryId);
    Category parentCategory = getParentCategory(request.getParentId());
    category.update(request, parentCategory);
    return CategoryResponse.from(category);
  }

  public void deleteCategory(Long categoryId) {
    Category category = getCategory(categoryId);
    validateDeletable(categoryId);
    categoryRepository.delete(category);
  }

  private Category getCategory(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));
  }

  private Category getParentCategory(Long categoryParentId) {
    if(categoryParentId == null) return null;

    return categoryRepository.findById(categoryParentId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PARENT_CATEGORY));
  }

  private void validateNotSelfAsParent(Long categoryId, Long parentId) {
    if(parentId == null) return;

    if(Objects.equals(categoryId, parentId)) {
      throw new CustomException(ErrorCode.CANNOT_SET_SELF_AS_PARENT_CATEGORY);
    }
  }

  private void validateDeletable(Long categoryId) {
    if(categoryRepository.existsByParentId(categoryId)) {
      throw new CustomException(ErrorCode.CATEGORY_HAS_CHILDREN);
    }

    if(productRepository.existsByCategoryId(categoryId)) {
      throw new CustomException(ErrorCode.CATEGORY_HAS_PRODUCTS);
    }
  }
}
