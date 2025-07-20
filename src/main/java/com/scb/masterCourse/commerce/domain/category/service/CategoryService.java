package com.scb.masterCourse.commerce.domain.category.service;

import com.scb.masterCourse.commerce.domain.category.dto.CategoryResponse;
import com.scb.masterCourse.commerce.domain.category.entity.Category;
import com.scb.masterCourse.commerce.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponse> findCategoriesHierarchy() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            return null;
        }

        List<CategoryResponse> rootCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.getParent() == null) {
                rootCategories.add(buildCategoryResponse(category, categories));
            }
        }

        return rootCategories;
    }

    private CategoryResponse buildCategoryResponse(Category category, List<Category> allCategories) {
        List<CategoryResponse> childResponses = new ArrayList<>();
        for (Category child : allCategories) {
            if (child.getParent() != null && child.getParent()
                .getId()
                .equals(category.getId())) {
                childResponses.add(buildCategoryResponse(child, allCategories));
            }
        }

        return CategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .children(childResponses.isEmpty() ? null : childResponses)
            .build();
    }
}
