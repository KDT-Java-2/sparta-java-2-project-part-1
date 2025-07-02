package com.dogworld.dogdog.domain.category;

import com.dogworld.dogdog.api.category.request.CategoryRequest;
import com.dogworld.dogdog.api.category.response.CategoryCreateResponse;
import com.dogworld.dogdog.api.category.response.CategoryResponse;
import com.dogworld.dogdog.domain.category.repository.CategoryRepository;
import com.dogworld.dogdog.domain.category.repository.FlatCategoryDto;
import com.dogworld.dogdog.domain.product.repository.ProductRepository;
import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public List<CategoryResponse> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    return categories.stream()
        .map(CategoryResponse::from)
        .collect(Collectors.toList());
  }

  public List<CategoryResponse> getAllCategoriesHierarchy() {
    List<FlatCategoryDto> flatList = categoryRepository.findAllFlat();

    Map<Long, CategoryResponse> map = buildCategoryMap(flatList);
    List<CategoryResponse> roots = buildCategoryTree(flatList, map);
    sortTree(roots);

    return roots;
  }

  @Transactional
  public CategoryCreateResponse createCategory(CategoryRequest request) {

    Category parent = getParentCategory(request.getParentId());

    Category createdCategory = Category.create(request, parent);
    Category savedCategory = categoryRepository.save(createdCategory);
    return CategoryCreateResponse.from(savedCategory);
  }

  @Transactional
  public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
    validateNotSelfAsParent(categoryId, request.getParentId());
    Category category = getCategory(categoryId);
    Category parentCategory = getParentCategory(request.getParentId());
    category.update(request, parentCategory);
    return CategoryResponse.from(category);
  }

  @Transactional
  public void deleteCategory(Long categoryId) {
    Category category = getCategory(categoryId);
    validateDeletable(categoryId);
    categoryRepository.delete(category);
  }

  private Map<Long, CategoryResponse> buildCategoryMap(List<FlatCategoryDto> flatList) {
    Map<Long, CategoryResponse> map = new HashMap<>();

    for(FlatCategoryDto flatCategoryDto : flatList) {
      CategoryResponse response = CategoryResponse.from(flatCategoryDto);
      map.put(flatCategoryDto.getId(), response);
    }
    return map;
  }

  private static List<CategoryResponse> buildCategoryTree(List<FlatCategoryDto> flatList,
                                                            Map<Long, CategoryResponse> map) {
    List<CategoryResponse> roots = new ArrayList<>();

    for(FlatCategoryDto dto : flatList) {
      CategoryResponse child = map.get(dto.getId());
      Long parentId = dto.getParentId();

      if(parentId == null) {
        roots.add(child);
      } else {
        CategoryResponse parent = map.get(parentId);
        if(parent != null) {
          parent.getChildren().add(child);
        }
      }
    }
    return roots;
  }

  private void sortTree(List<CategoryResponse> nodes) {
    nodes.sort(Comparator.comparingInt(CategoryResponse::getSortOrder));
    for(CategoryResponse node : nodes) {
      sortTree(node.getChildren());
    }
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



