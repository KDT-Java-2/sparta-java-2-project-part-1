package com.moveuk.ecommerce.application.admin.category;

import com.moveuk.ecommerce.application.admin.category.request.AdminCategoryInfo;
import com.moveuk.ecommerce.application.admin.category.response.AdminCategoryResult;
import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.admin.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryFacade {

    private final AdminCategoryService adminCategoryService;

    public AdminCategoryResult.CategoryRegisterV1 createCategory(AdminCategoryInfo.CategoryRegisterV1 from) {
        Category category = Category.builder()
                .name(from.name())
                .description(from.description())
                .build();
        adminCategoryService.createCategory(category);
        return new AdminCategoryResult.CategoryRegisterV1(category.getId());

    }

    public List<AdminCategoryResult.CategoryTreeDto> updateCategory(AdminCategoryInfo.CategoryRegisterV2 from) {

        return null;
    }

    public Void removeCategory(Long categoryId) {
        return null;
    }
}
