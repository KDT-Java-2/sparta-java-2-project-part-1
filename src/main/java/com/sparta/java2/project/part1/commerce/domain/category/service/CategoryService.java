package com.sparta.java2.project.part1.commerce.domain.category.service;

import com.sparta.java2.project.part1.commerce.domain.category.dto.CategorySearchHierarchyResponse;
import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import com.sparta.java2.project.part1.commerce.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategorySearchHierarchyResponse> searchHierarchy() {
        List<CategorySearchHierarchyResponse> roots = new ArrayList<>();

        List<Category> categories = categoryRepository.findAll();
        Map<Long, CategorySearchHierarchyResponse> flatMap = categories.stream()
                .collect(Collectors.toMap(
                        Category::getId,
                        category -> new CategorySearchHierarchyResponse(category.getId(), category.getName())
                ));

        for (Category category : categories) {
            // node loop
            CategorySearchHierarchyResponse node = flatMap.get(category.getId());
            Long nodeParentId = category.getParent().getId();
            if (flatMap.containsKey(nodeParentId)) {
                flatMap.get(nodeParentId).addChild(node);
            } else {
                roots.add(node);
            }
        }

        return roots;
    }
}
