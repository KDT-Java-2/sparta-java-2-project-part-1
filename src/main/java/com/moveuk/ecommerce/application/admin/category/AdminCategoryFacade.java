package com.moveuk.ecommerce.application.admin.category;

import com.moveuk.ecommerce.application.admin.category.request.AdminCategoryInfo;
import com.moveuk.ecommerce.application.admin.category.response.AdminCategoryResult;
import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.admin.AdminCategoryService;
import com.moveuk.ecommerce.domain.product.admin.AdminProductService;
import com.moveuk.ecommerce.support.EcommerceErrorCode;
import com.moveuk.ecommerce.support.EcommerceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryFacade {

    private final AdminCategoryService adminCategoryService;
    private final AdminProductService adminProductService;

    @Transactional
    public AdminCategoryResult.CategoryRegisterV1 createCategory(AdminCategoryInfo.CategoryRegisterV1 from) {
        Category category = Category.builder()
                .name(from.name())
                .description(from.description())
                .build();

        adminCategoryService.createCategory(category, from.parentId());

        return new AdminCategoryResult.CategoryRegisterV1(category.getId());

    }

    @Transactional
    public List<AdminCategoryResult.CategoryTreeDto> updateCategory(AdminCategoryInfo.CategoryRegisterV2 from) {
        Category category = adminCategoryService.findById(from.categoryId());

        adminCategoryService.validateParentChange(category, from.parentId());

        Category newParent = (from.parentId() != null)
                ? adminCategoryService.findById(from.parentId())
                : null;
        // 2. 자기 자신을 부모로 지정하려는 경우

        category.updateDetail(from.name(), from.description(), newParent);

        return List.of(AdminCategoryResult.CategoryTreeDto.from(category));
    }

    @Transactional
    public Void removeCategory(Long categoryId) {

        Category category = adminCategoryService.findById(categoryId);

        if (adminCategoryService.hasChildren(category)) {
            throw new EcommerceException(EcommerceErrorCode.CATEGORY_HAS_CHILDREN);
        }

        if (adminProductService.hasProductInCategory(category.getId())) {
            throw new EcommerceException(EcommerceErrorCode.CATEGORY_HAS_PRODUCTS);
        }

        return adminCategoryService.removeCategory(categoryId);
    }
}
