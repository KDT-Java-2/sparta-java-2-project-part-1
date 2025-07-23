package com.sparta.bootcamp.java_2_example.domain.category.service;

import com.sparta.bootcamp.java_2_example.domain.category.dto.CategoryHierarchyResponse;
import com.sparta.bootcamp.java_2_example.domain.category.entity.Category;
import com.sparta.bootcamp.java_2_example.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryHierarchyResponse> getAllHierarchy() {
        return getCategoryHierarchy();
    }

    private List<CategoryHierarchyResponse> getCategoryHierarchy() {
        List<Category> categories = categoryRepository.findAll();

        Map<Long, CategoryHierarchyResponse> responseMap = new HashMap<>();

        for (Category category : categories) {
            CategoryHierarchyResponse response = CategoryHierarchyResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .children(new ArrayList<>())
                    .build();

            responseMap.put(category.getId(), response);
        }

        List<CategoryHierarchyResponse> rootCategories = new ArrayList<>();
        for (Category category : categories) {
            CategoryHierarchyResponse response = responseMap.get(category.getId());

            if (ObjectUtils.isEmpty(category.getParent())) {
                rootCategories.add(response);
            } else {
                CategoryHierarchyResponse parentResponse = responseMap.get(category.getParent().getId());
                if (!ObjectUtils.isEmpty(parentResponse)) {
                    parentResponse.getChildren().add(response);
                }
            }
        }
        return rootCategories;
    }
}
