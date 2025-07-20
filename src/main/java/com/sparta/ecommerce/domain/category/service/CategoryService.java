package com.sparta.ecommerce.domain.category.service;


import com.sparta.ecommerce.common.exception.ServiceException;
import com.sparta.ecommerce.common.exception.ServiceExceptionCode;
import com.sparta.ecommerce.domain.category.dto.CategoryCreateRequest;
import com.sparta.ecommerce.domain.category.dto.CategoryCreateResponse;
import com.sparta.ecommerce.domain.category.dto.CategoryInfoResponse;
import com.sparta.ecommerce.domain.category.dto.CategoryResponse;
import com.sparta.ecommerce.domain.category.entity.Category;
import com.sparta.ecommerce.domain.category.mapper.CategoryMapper;
import com.sparta.ecommerce.domain.category.repository.CategoryRepository;
import com.sparta.ecommerce.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public List<CategoryResponse> getCategoryTree() {
    List<Category> allCategories = categoryRepository.findAll();

    // ID로 카테고리 매핑
    Map<Long, CategoryResponse> map = new HashMap<>();
    List<CategoryResponse> roots = new ArrayList<>();

    for (Category category : allCategories) {
      CategoryResponse dto = new CategoryResponse(category.getId(), category.getCategoryNm());
      map.put(dto.getId(), dto);
    }

    for (Category category : allCategories) {
      CategoryResponse dto = map.get(category.getId());
      if (category.getParent() != null) {
        CategoryResponse parentDto = map.get(category.getParent().getId());
        parentDto.getChildren().add(dto);
      } else {
        roots.add(dto); // 최상위 노드
      }
    }

    return roots;
  }


  @Transactional
  public CategoryCreateResponse save(CategoryCreateRequest request) {
    Category parent = null;
    if (request.getParentId() != null) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category category = categoryMapper.toEntity(request, parent);

    Category result = categoryRepository.save(category);

    return categoryMapper.toCategoryCreateResponse(result);
  }

  @Transactional
  public CategoryInfoResponse update(Long categoryId, CategoryCreateRequest request) {
    Category parentCategory = null;
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (request.getParentId() != null) {
      // 2-1. 자기 자신을 부모로 지정하는 경우
      if (category.getId().equals(request.getParentId())) {
        throw new ServiceException(ServiceExceptionCode.CANNOT_BE_SELF_PARENT);
      }

      parentCategory = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PARENT_CATEGORY));

      // 🔥 순환 참조 검사
      if (parentCategory != null && isCircularReference(category, parentCategory)) {
        throw new ServiceException(ServiceExceptionCode.CIRCULAR_REFERENCE);
      }
    }

    category.update(request, parentCategory);

    return categoryMapper.toCategoryResponse(category);
  }

  @Transactional
  public void delete(Long categoryId) {
    // 조건 1: 하위 카테고리 존재 여부 확인
    if (categoryRepository.existsByParentId(categoryId)) {
      throw new ServiceException(ServiceExceptionCode.EXISTS_CHILD_CATEGORY);
    }

    // 조건 2: 이 카테고리에 속한 상품 존재 여부 확인
    if (productRepository.existsByCategoryId(categoryId)) {
      throw new ServiceException(ServiceExceptionCode.EXISTS_CATEGORY_PRODUCTS);
    }

    Category category = categoryRepository.findById(categoryId).orElseThrow(
        () -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    categoryRepository.delete(category);
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
