package com.java_project.part_1.domain.admin.service;

import com.java_project.part_1.common.exception.ServiceException;
import com.java_project.part_1.common.exception.ServiceExceptionCode;
import com.java_project.part_1.domain.category.dto.CategoryRequest;
import com.java_project.part_1.domain.category.entity.Category;
import com.java_project.part_1.domain.category.repository.CategoryRepository;
import com.java_project.part_1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  // 카테고리 등록
  public void createCategory(CategoryRequest request) {
    Category parent = null;

    if (!ObjectUtils.isEmpty(request.getParentId())) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parent)
        .build();
    categoryRepository.save(category);
  }

  // 카테고리 수정
  public void updateCategory(Long categoryId, CategoryRequest request) {
    Category parent = null;

    if (!ObjectUtils.isEmpty(request.getParentId())) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setParent(parent);

    categoryRepository.save(category);
  }

  // 카테고리 삭제
  public void deleteCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    boolean hasChildren = categoryRepository.existsByParent_Id(categoryId);
    if (hasChildren) {
      throw new ServiceException(ServiceExceptionCode.EXIST_CHILDREN_CATEGORY);
    }

    boolean hasProducts = productRepository.existsByCategory_Id(categoryId);
    if (hasProducts) {
      throw new ServiceException(ServiceExceptionCode.EXIST_CATEGORY_PRODUCT);
    }

    categoryRepository.delete(category);
  }

}
