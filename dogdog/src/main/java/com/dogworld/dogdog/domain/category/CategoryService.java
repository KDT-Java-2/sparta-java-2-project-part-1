package com.dogworld.dogdog.domain.category;

import com.dogworld.dogdog.api.category.response.CategoryResponse;
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
}
