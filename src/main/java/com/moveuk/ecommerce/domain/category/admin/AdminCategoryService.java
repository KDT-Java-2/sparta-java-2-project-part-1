package com.moveuk.ecommerce.domain.category.admin;

import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.support.EcommerceErrorCode;
import com.moveuk.ecommerce.support.EcommerceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    public Category findById(Long id) {
        return adminCategoryRepository.findById(id)
                .orElseThrow(()->new EcommerceException(EcommerceErrorCode.NOT_FOUND_CATEGORY));
    }

    public void createCategory(Category category, long parentId) {

        if(parentId > 0) {
            Category parent = adminCategoryRepository.findById(parentId)
                    .orElseThrow(() -> new EcommerceException(EcommerceErrorCode.NOT_FOUND_CATEGORY));
            category.setParent(parent);
        }

        adminCategoryRepository.createCategory(category);

    }

    public boolean isCircularReference(Category current, Category newParent) {
        Category parent = newParent;
        while (parent != null) {
            if (parent.getId().equals(current.getId())) {
                return true; // 순환 참조 발생!
            }
            parent = parent.getParent(); // 위로 계속 올라가면서 확인
        }
        return false;
    }

    public void validateParentChange(Category target, Long newParentId) {
        if (newParentId == null) return;

        if (target.getId().equals(newParentId)) {
            throw new EcommerceException(EcommerceErrorCode.CATEGORY_CANNOT_BE_ITS_OWN_PARENT);
        }

        Category newParent = findById(newParentId);

        if (isCircularReference(target, newParent)) {
            throw new EcommerceException(EcommerceErrorCode.CATEGORY_CIRCULAR_REFERENCE);
        }
    }

    public Void removeCategory(Long categoryId) {

        Category category = adminCategoryRepository.findById(categoryId)
                .orElseThrow(()->new EcommerceException(EcommerceErrorCode.NOT_FOUND_CATEGORY));
        category.removeCategory();

        return null;
    }

    public boolean hasChildren(Category category) {
        return adminCategoryRepository.existsByParent(category);
    }
}
