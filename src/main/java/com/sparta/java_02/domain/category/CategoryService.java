package com.sparta.java_02.domain.category;

import com.sparta.java_02.domain.category.dto.CategoryCreateRequest;
import com.sparta.java_02.domain.category.dto.CategoryResponse;
import com.sparta.java_02.domain.product.ProductRepository;
import com.sparta.java_02.global.exception.BusinessException;
import com.sparta.java_02.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    /**
     * 카테고리 전체 계층 구조 조회
     */
    public List<CategoryResponse> getCategoryHierarchy() {
        // 모든 카테고리를 한 번에 조회
        List<Category> allCategories = categoryRepository.findAll();
        
        // 최상위 카테고리만 필터링
        List<Category> rootCategories = allCategories.stream()
            .filter(category -> category.getParent() == null)
            .collect(Collectors.toList());

        // 계층 구조를 유지하면서 CategoryResponse로 변환
        return rootCategories.stream()
            .map(CategoryResponse::from)
            .collect(Collectors.toList());
    }

    /**
     * 카테고리 등록 (관리자)
     */
    @Transactional
    public Long createCategory(CategoryCreateRequest request) {
        Category parent = null;
        
        // 부모 카테고리가 지정된 경우 검증
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
        }

        // 카테고리 생성
        Category category = new Category(
            request.getName(),
            request.getDescription(),
            parent,
            0  // displayOrder는 기본값 0
        );

        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }

    /**
     * 카테고리 수정 (관리자)
     */
    @Transactional
    public CategoryResponse updateCategory(Long categoryId, CategoryCreateRequest request) {
        // 카테고리 존재 검증
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

        Category newParent = null;
        
        // 부모 카테고리 변경 시 검증
        if (request.getParentId() != null) {
            // 자기 자신을 부모로 지정하는 것 방지
            if (request.getParentId().equals(categoryId)) {
                throw new BusinessException(ErrorCode.CIRCULAR_REFERENCE);
            }
            
            newParent = categoryRepository.findById(request.getParentId())
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
            
            // 순환 참조 방지 - 자신의 하위 카테고리를 부모로 설정하는 것 방지
            if (isDescendant(newParent, category)) {
                throw new BusinessException(ErrorCode.CIRCULAR_REFERENCE);
            }
        }

        // 카테고리 정보 수정
        category.updateInfo(request.getName(), request.getDescription(), 0);
        
        // 부모 변경 처리
        if (category.getParent() != newParent) {
            if (category.getParent() != null) {
                category.getParent().removeChild(category);
            }
            if (newParent != null) {
                newParent.addChild(category);
            }
        }

        return CategoryResponse.from(category);
    }

    /**
     * 카테고리 삭제 (관리자)
     */
    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

        // 하위 카테고리 존재 검증
        if (category.hasChildren()) {
            throw new BusinessException(ErrorCode.CATEGORY_HAS_CHILDREN);
        }

        // 카테고리에 속한 상품 존재 검증
        boolean hasProducts = productRepository.findByCategoryId(categoryId).size() > 0;
        if (hasProducts) {
            throw new BusinessException(ErrorCode.CATEGORY_HAS_PRODUCTS);
        }

        categoryRepository.delete(category);
    }

    /**
     * 순환 참조 검증 - newParent가 category의 하위 카테고리인지 확인
     */
    private boolean isDescendant(Category potentialParent, Category category) {
        Category current = potentialParent.getParent();
        while (current != null) {
            if (current.getId().equals(category.getId())) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }
} 