package com.sparta.javamarket.domain.category.service;

import com.sparta.javamarket.domain.category.dto.CategoryResponse;
import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryResponse> getCategoryHierarchy() {
    List<Category> categories = categoryRepository.findAll();
    Map<Long, CategoryResponse> categoryResponseMap = new HashMap<>();

    for (Category category : categories) {
      CategoryResponse response = CategoryResponse.builder()
          .id(category.getId())
          .name(category.getName())
          .children(new ArrayList<>())
          .build();

      categoryResponseMap.put(category.getId(), response);
    }

    List<CategoryResponse> rootCategories = new ArrayList<>();
    for(Category category : categories) {
      CategoryResponse categoryResponse = categoryResponseMap.get(category.getId());
      if(ObjectUtils.isEmpty(category.getParent())){
        rootCategories.add(categoryResponse);
      }else{
        CategoryResponse parentResponse = categoryResponseMap.get(category.getParent().getId());
        if(!ObjectUtils.isEmpty(parentResponse)){
          parentResponse.getChildren().add(categoryResponse);
        }
      }
    }

    return rootCategories;

  }

}
