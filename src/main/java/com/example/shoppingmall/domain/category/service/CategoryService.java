package com.example.shoppingmall.domain.category.service;

import com.example.shoppingmall.common.exception.ServiceException;
import com.example.shoppingmall.common.exception.ServiceExceptionCode;
import com.example.shoppingmall.domain.category.dto.CategoryHierarchyResponse;
import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.category.repository.CategoryRepository;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
        Category uncategorized = categoryRepository.findByName("미분류");
        if (uncategorized == null) {
            throw new ServiceException(ServiceExceptionCode.UNCATEGORIZED_CATEGORY_NOT_FOUND);
        }
        List<Product> products = productRepository.findByCategory(category);
        for (Product product : products) {
            product.setCategory(uncategorized);
        }
        categoryRepository.delete(category);
    }

    @Transactional
    public List<CategoryHierarchyResponse> getCategoryHierarchy() {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, CategoryHierarchyResponse> map = new HashMap<>();
        List<CategoryHierarchyResponse> roots = new ArrayList<>();

        // 1. 모든 카테고리를 DTO로 변환해 map에 저장
        for (Category c : categories) {
            map.put(c.getId(), new CategoryHierarchyResponse(c.getId(), c.getName(), new ArrayList<>()));
        }

        // 2. parentId 기준으로 트리 구조 조립
        for (Category c : categories) {
            CategoryHierarchyResponse dto = map.get(c.getId());
            if (c.getParent() == null) {
                roots.add(dto);
            } else {
                map.get(c.getParent().getId()).getChildren().add(dto);
            }
        }
        return roots;
    }
}
