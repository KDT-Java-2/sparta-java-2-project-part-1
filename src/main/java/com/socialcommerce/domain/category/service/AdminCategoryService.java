package com.socialcommerce.domain.category.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.category.dto.CategoryRequest;
import com.socialcommerce.domain.category.dto.CategoryResponse;
import com.socialcommerce.domain.category.entity.Category;
import com.socialcommerce.domain.category.repository.CategoryRepository;
import com.socialcommerce.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  @Transactional
  public Long createCategory(CategoryRequest request){
    Category parent = null;

    if (request.getParentId() != null) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_EXIST_CATEGORY));
    }

    Category saved = categoryRepository.save(
        Category.builder()
            .name(request.getName())
            .description(request.getDescription())
            .parent(parent)
            .build()
    );

    return saved.getId();
  }

  @Transactional
  public CategoryResponse updateCategory(Long categoryId, CategoryRequest request){

    Category category = categoryRepository.findById(categoryId).
        orElseThrow(()->new ServiceException(ServiceExceptionCode.NOT_EXIST_CATEGORY));

    Category parent = null;
    if (request.getParentId() != null) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_EXIST_PARENT_CATEGORY));
    }

    category.update(request.getName(), request.getDescription(), parent);

    return CategoryResponse.builder()
        .name(category.getName())
        .description(category.getDescription())
        .parentId(category.getParent() != null ? category.getParent().getId() : null)
        .build();
  }

  @Transactional
  public void deleteCategory(Long categoryId){
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_EXIST_CATEGORY));

    if(categoryRepository.existsByParent(category)){
      throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_CATEGORY_EXIST_SUBCATEGORY);
    }

    if(productRepository.existsByCategory(category)){
      throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_CATEGORY_EXIST_PRODUCTS);
    }

    categoryRepository.delete(category);
  }
}
