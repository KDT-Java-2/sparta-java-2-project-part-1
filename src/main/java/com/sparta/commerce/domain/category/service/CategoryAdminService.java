package com.sparta.commerce.domain.category.service;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.commerce.domain.category.dto.CategoryUpdateResponse;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryAdminService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public Long createCategory(CategoryCreateRequest request) {

    Category categoryParent = categoryRepository.findById(request.getParentId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PARENT_CATEGORY));

    Category newCategory = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(categoryParent)
        .build();

    Category savedCategory = categoryRepository.save(newCategory);

    return savedCategory.getId();
  }

  public CategoryUpdateResponse updateCategory(CategoryCreateRequest request, Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY_BY_ID));

    if(Objects.equals(request.getParentId(), categoryId)) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_CANNOT_BE_ITS_OWN_PARENT);
    }

    if (request.getName() != null && !request.getName().equals(category.getName())) {
      if (categoryRepository.existsByName(request.getName())) {
        throw new ServiceException(ServiceExceptionCode.DUPLICATE_CATEGORY_NAME);
      }
      category.setName(request.getName());
    }

    if (request.getDescription() != null) {
      category.setDescription(request.getDescription());
    }

    Category updatedCategory = categoryRepository.save(category);

    return CategoryUpdateResponse.builder()
        .id(updatedCategory.getId())
        .name(updatedCategory.getName())
        .description(updatedCategory.getDescription())
        .parentId(updatedCategory.getParent())
        .build();
  }

  public void deleteCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY_BY_ID));

    if(categoryRepository.existsByParentId(categoryId)) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_HAS_CHILDREN);
    }

    if(productRepository.existsByCategoryId(categoryId)) {
      throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_CATEGORY_WITH_PRODUCTS);
    }

    categoryRepository.delete(category);
  }

}
