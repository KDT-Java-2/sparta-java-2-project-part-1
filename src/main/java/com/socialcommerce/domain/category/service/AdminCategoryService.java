package com.socialcommerce.domain.category.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.category.dto.CategoryRequest;
import com.socialcommerce.domain.category.dto.CategoryResponse;
import com.socialcommerce.domain.category.entity.Category;
import com.socialcommerce.domain.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

  private final CategoryRepository categoryRepository;

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

    return saved.getId();  // 생성된 카테고리 id 반환!
  }

  @Transactional
  public CategoryResponse updateCategory(Long parentId, CategoryRequest request){

    return null;
  }
}
