package com.sparta.coupang_commerce.domain.category.service;

import com.sparta.coupang_commerce.common.exception.ServiceException;
import com.sparta.coupang_commerce.common.exception.ServiceExceptionCode;
import com.sparta.coupang_commerce.domain.category.dto.CategoryResponse;
import com.sparta.coupang_commerce.domain.category.dto.admin.CategoryCreateOrUpdateRequest;
import com.sparta.coupang_commerce.domain.category.dto.admin.CategoryCreateResponse;
import com.sparta.coupang_commerce.domain.category.entity.Category;
import com.sparta.coupang_commerce.domain.category.mapper.CategoryMapper;
import com.sparta.coupang_commerce.domain.category.repository.CategoryRepository;
import com.sparta.coupang_commerce.domain.product.entity.Product;
import com.sparta.coupang_commerce.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final CategoryMapper categoryMapper;

  public CategoryCreateResponse createCategory(CategoryCreateOrUpdateRequest request) {
    Category newCategory = categoryMapper.toCategory(request);
    if (request.getParentId() == null) {
      newCategory.addParent(null);
    } else {
      Category parentCategory = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
      newCategory.addParent(parentCategory);
    }

    categoryRepository.save(newCategory);

    return categoryMapper.toCategoryCreateResponse(newCategory);
  }

  @Transactional
  public List<CategoryResponse> updateCategory(Long categoryId, CategoryCreateOrUpdateRequest request) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (Objects.equals(request.getParentId(), category.getId())) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
    }

    category.updateName(request.getName());
    category.updateParent(category);

    List<Category> categories = categoryRepository.findAll();

    return generateCategoryHierarchy(categories);
  }


  @Transactional
  public Void deleteCategory(Long categoryId) {
    List<Category> childCategory = categoryRepository.findByParent_Id(categoryId);
    List<Product> products = productRepository.findByCategory_Id(categoryId);
    // 현재 카테고리 하위에 다른 카테고리가 없고 동시에 현재 카테고리에 속한 상품이 없는 경우
    if (childCategory.isEmpty() && products.isEmpty()) {
      categoryRepository.deleteById(categoryId);
    } else {
      throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_CATEGORY);
    }

    return null;
  }

  @Transactional
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
