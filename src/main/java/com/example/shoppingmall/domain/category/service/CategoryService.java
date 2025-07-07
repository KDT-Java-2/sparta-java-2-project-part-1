package com.example.shoppingmall.domain.category.service;

import com.example.shoppingmall.common.exception.ServiceException;
import com.example.shoppingmall.common.exception.ServiceExceptionCode;
import com.example.shoppingmall.domain.category.dto.CategoryHierarchyResponse;
import com.example.shoppingmall.domain.category.dto.CategoryCreateRequest;
import com.example.shoppingmall.domain.category.dto.CategoryCreateResponse;
import com.example.shoppingmall.domain.category.dto.CategoryUpdateRequest;
import com.example.shoppingmall.domain.category.dto.CategoryUpdateResponse;
import com.example.shoppingmall.domain.category.dto.CategoryDeleteResponse;
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
    public CategoryDeleteResponse deleteCategory(Long categoryId) {
        // 1. 카테고리 존재 검증
        Category category = validateCategoryExists(categoryId);

        // 2. 하위 카테고리 존재 검증 (조건 1)
        if (!category.getChildren().isEmpty()) {
            throw new ServiceException(ServiceExceptionCode.CATEGORY_HAS_CHILDREN);
        }

        // 3. 카테고리에 속한 상품 존재 검증 (조건 2)
        List<Product> products = productRepository.findByCategory(category);
        if (!products.isEmpty()) {
            throw new ServiceException(ServiceExceptionCode.CATEGORY_HAS_PRODUCTS);
        }

        // 4. 두 조건을 모두 만족하므로 카테고리 삭제
        categoryRepository.delete(category);

        // 5. 응답 생성
        return new CategoryDeleteResponse(true, categoryId);
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

    @Transactional
    public CategoryCreateResponse createCategory(CategoryCreateRequest request) {
        // 1. 카테고리명 필수 검증
        validateCategoryName(request.getName());

        // 2. 부모 카테고리 존재 검증 (parentId가 있는 경우)
        Category parentCategory = validateParentCategory(request.getParentId());

        // 3. 카테고리 생성
        Category category = Category.builder()
                .name(request.getName().trim())
                .parent(parentCategory)
                .build();

        // 4. 카테고리 저장
        Category savedCategory = categoryRepository.save(category);

        // 5. 응답 생성
        return new CategoryCreateResponse(savedCategory.getId());
    }

    @Transactional
    public CategoryUpdateResponse updateCategory(Long categoryId, CategoryUpdateRequest request) {
        // 1. 카테고리 존재 검증
        Category category = validateCategoryExists(categoryId);

        // 2. 카테고리명 필수 검증
        validateCategoryName(request.getName());

        // 3. 순환 참조 검증 (자기 자신을 부모로 지정하는 경우)
        validateCircularReference(categoryId, request.getParentId());

        // 4. 부모 카테고리 존재 검증 (parentId가 있는 경우)
        Category parentCategory = validateParentCategory(request.getParentId());

        // 5. 카테고리 정보 업데이트
        category.updateInfo(request.getName().trim(), parentCategory);

        // 6. 카테고리 저장
        Category savedCategory = categoryRepository.save(category);

        // 7. 응답 생성
        return new CategoryUpdateResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getParent() != null ? savedCategory.getParent().getId() : null,
                savedCategory.getParent() != null ? savedCategory.getParent().getName() : null
        );
    }

    // === 공통 유틸리티 메서드들 ===

    /**
     * 카테고리 존재 검증
     */
    private Category validateCategoryExists(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
    }

    /**
     * 카테고리명 필수 검증
     */
    private void validateCategoryName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ServiceException(ServiceExceptionCode.INVALID_REQUEST);
        }
    }

    /**
     * 부모 카테고리 존재 검증 (parentId가 있는 경우)
     */
    private Category validateParentCategory(Long parentId) {
        if (parentId == null) {
            return null;
        }
        return categoryRepository.findById(parentId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
    }

    /**
     * 순환 참조 검증
     */
    private void validateCircularReference(Long categoryId, Long parentId) {
        if (parentId != null && parentId.equals(categoryId)) {
            throw new ServiceException(ServiceExceptionCode.CATEGORY_CIRCULAR_REFERENCE);
        }
    }
}
