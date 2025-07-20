package com.sparta.spartajava2projectpart1.domain.category.service;

import com.sparta.spartajava2projectpart1.domain.category.dto.CategoryResponse;
import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import com.sparta.spartajava2projectpart1.domain.category.repository.CategoryQueryRepository;
import com.sparta.spartajava2projectpart1.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    public List<CategoryResponse> getCategoriesWithHierarchy() {
        List<Category> categories = categoryQueryRepository.findCategoryHierarchy();

        Map<Long, CategoryResponse> map = new HashMap<>();
        for (Category category : categories) {
            map.put(category.getId(),
                    CategoryResponse.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build());
        }

        List<CategoryResponse> roots = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse response = map.get(category.getId());

            if (ObjectUtils.isEmpty(category.getParent())) {
                roots.add(response);
            } else {
                CategoryResponse parentResponse = map.get(category.getParent().getId());
                parentResponse.getChildren().add(response);
            }
        }
        return roots;
    }
}
