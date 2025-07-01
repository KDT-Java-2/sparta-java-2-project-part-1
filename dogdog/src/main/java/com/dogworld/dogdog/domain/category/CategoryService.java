package com.dogworld.dogdog.domain.category;

import com.dogworld.dogdog.api.category.request.CategoryRequest;
import com.dogworld.dogdog.api.category.response.CategoryResponse;
import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryResponse> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    return categories.stream()
        .map(CategoryResponse::from)
        .collect(Collectors.toList());
  }

  @Transactional
  public CategoryResponse createCategory(CategoryRequest request) {
    Category parent = null;

    parent = getParentCategory(request, parent);

    Category createdCategory = Category.create(request, parent);
    Category savedCategory = categoryRepository.save(createdCategory);
    return CategoryResponse.from(savedCategory);
  }

  private Category getParentCategory(CategoryRequest request, Category parent) {
    if(request.getParentId() != null) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));
    }
    return parent;
  }
}
