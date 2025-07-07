package com.sparta.part_1.domain.category.service;


import com.sparta.part_1.common.exception.CategoryErrorCode;
import com.sparta.part_1.common.exception.CategoryException;
import com.sparta.part_1.domain.category.dto.request.CategoryAddRequest;
import com.sparta.part_1.domain.category.dto.request.CategoryEditRequest;
import com.sparta.part_1.domain.category.dto.response.CategoryAddResponse;
import com.sparta.part_1.domain.category.dto.response.CategoryEditResponse;
import com.sparta.part_1.domain.category.dto.response.CategoryResponse;
import com.sparta.part_1.domain.category.entity.Category;
import com.sparta.part_1.domain.category.repository.CategoryQueryRepository;
import com.sparta.part_1.domain.category.repository.CategoryRepository;
import com.sparta.part_1.domain.product.repository.ProductQueryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryQueryRepository categoryQueryRepository;
  private final CategoryRepository categoryRepository;
  private final ProductQueryRepository productQueryRepository;

  public List<CategoryResponse> getAllCategories() {
    return categoryQueryRepository.findAll();
  }

  @Transactional
  public CategoryAddResponse addCategories(@Valid CategoryAddRequest request) {
    Category parent = categoryRepository.findById(request.getParentId())
        .orElseThrow(() -> new CategoryException(CategoryErrorCode.NOT_FOUND_PARENT));

    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parent)
        .build();

    Category saveResponse = categoryRepository.save(category);

    return CategoryAddResponse.builder()
        .categoryId(saveResponse.getId()).build();
  }

  @Transactional
  public CategoryEditResponse editCategories(CategoryEditRequest request, Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryException(CategoryErrorCode.HAS_NOT_FOUND_CATEGORY));

    Category parent = categoryRepository.findById(request.getParentId())
        .orElseThrow(() -> new CategoryException(CategoryErrorCode.NOT_FOUND_PARENT));

    category.update(request.getName(), request.getDescription(), parent);

    return CategoryEditResponse.builder()
        .categoryId(category.getId())
        .parentId(category.getParent().getId())
        .name(category.getName())
        .description(category.getDescription())
        .build();
  }


  @Transactional
  public void deleteCategories(@NotNull Long categoryId) {
    Long childCategoryCount = categoryQueryRepository.findChildCategoryCount(categoryId);
    Long productCountInCategory = productQueryRepository.findProductCountInCategory(categoryId);

    if (productCountInCategory == null || productCountInCategory != 0) {
      throw new CategoryException(CategoryErrorCode.HAS_PRODUCT_IN_CATEGORY);
    }

    if (childCategoryCount == null || childCategoryCount != 0) {
      throw new CategoryException(CategoryErrorCode.HAS_CHILD_CATEGORY);
    }

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryException(CategoryErrorCode.HAS_NOT_FOUND_CATEGORY));

    categoryRepository.delete(category);
  }
}
