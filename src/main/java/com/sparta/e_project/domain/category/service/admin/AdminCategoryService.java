package com.sparta.e_project.domain.category.service.admin;

import com.sparta.e_project.common.exception.ServiceException;
import com.sparta.e_project.common.exception.ServiceExceptionCode;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryCreateRequest;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryCreateResponse;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryUpdateRequest;
import com.sparta.e_project.domain.category.dto.admin.AdminCategoryUpdateResponse;
import com.sparta.e_project.domain.category.entity.Category;
import com.sparta.e_project.domain.category.repository.admin.AdminCategoryRepository;
import com.sparta.e_project.domain.product.repository.admin.AdminProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

  private final AdminCategoryRepository adminCategoryRepository;

  private final AdminProductRepository adminProductRepository;

  @Transactional
  public AdminCategoryCreateResponse createCategory(AdminCategoryCreateRequest createRequest) {

    Category parent = null;

    if (adminCategoryRepository.existsByName(createRequest.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_CATEGORY_NAME);
    }

    if (!ObjectUtils.isEmpty(createRequest.getParentId())) {
      parent = adminCategoryRepository.findById(createRequest.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category category = Category.builder()
        .name(createRequest.getName())
        .description(createRequest.getDescription())
        .parent(parent)
        .build();

    adminCategoryRepository.save(category);

    return AdminCategoryCreateResponse.builder()
        .id(category.getId())
        .build();
  }

  @Transactional
  public AdminCategoryUpdateResponse updateCategory(Long categoryId,
      AdminCategoryUpdateRequest updateRequest) {
    Category category = adminCategoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    // Check if the new name already exists in another category
    if (!category.getName().equals(updateRequest.getName()) &&
        adminCategoryRepository.existsByName(updateRequest.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_CATEGORY_NAME);
    }

    Category parent = null;
    // 부모 카테고리가 있는 경우
    if (updateRequest.getParentId() != null) {
      if (category.getId().equals(updateRequest.getParentId())) {
        throw new ServiceException(ServiceExceptionCode.CATEGORY_MYSELF);
      }

      parent = adminCategoryRepository.findById(updateRequest.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PARENT_CATEGORY));
      //
      if (!ObjectUtils.isEmpty(parent) && isCircularReference(category, parent)) {
        throw new ServiceException(ServiceExceptionCode.CIRCULAR_REFERENCE);
      }
    }
    category.putCategory(updateRequest.getName(), updateRequest.getDescription(), parent);

    return AdminCategoryUpdateResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .parentId(category.getParent() != null ? category.getParent().getId() : null)
        .build();
  }

  @Transactional
  public void deleteCategory(Long categoryId) {
    Category category = adminCategoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (adminCategoryRepository.existsByParent(category)) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_HAS_SUBCATEGORIES);
    }

    if (adminProductRepository.existsByCategory(category)) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_HAS_PRODUCTS);
    }

    // 4. 카테고리 삭제
    adminCategoryRepository.delete(category);
  }

  private boolean isCircularReference(Category target, Category newParent) {
    Category current = newParent;
    while (current != null) {
      if (current.getId().equals(target.getId())) {
        return true; // 순환 발견
      }
      current = current.getParent(); // 계속 위로 타고 올라감
    }
    return false;
  }
}