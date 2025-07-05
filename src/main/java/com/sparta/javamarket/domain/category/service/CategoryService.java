package com.sparta.javamarket.domain.category.service;

import com.sparta.javamarket.domain.category.dto.CategoryResponse;
import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<Category> getAllCategories(){
    List<Category> categories = categoryRepository.findAll();
//
//    Map<Integer, CategoryResponse> resultCategoryMap = categories.stream()
//        .collect(Collectors.toMap(
//            Category::getId,
//            category -> new CategoryResponse(category.getId(), category.getName(), categoryRepository.findAllById(category.getParent()))
//        ));
//
//    Map<Integer, CategoryResponse> categoryMapResponse = new HashMap();
//
//    for (Category category : categories) {
//      System.out.println("@name@ : " + category.getName() + " / parent : " + category.getParent());
//      Integer i = 1;
//
//
//      if(!StringUtils.isEmpty(category.getParent())) {
//        List<Category> childCategory = categoryRepository.findAllById(category.getParent().getId());
//        for(Category child : childCategory) {
//          System.out.println("@child name@ : " + child.getName() + " / "  + child.getId());
//        }
//      }
//    }
    return categories;
  }
}
