package com.moveuk.ecommerce.application.admin.category;

import com.moveuk.ecommerce.application.admin.category.request.AdminCategoryInfo;
import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.admin.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminCategoryFacade {

    private final AdminCategoryService adminCategoryService;

    public Map createCategory(AdminCategoryInfo.CategoryRegisterV1 from) {
        Category category = Category.builder()
                .name(from.name())
                .description(from.description())
                .build();
        adminCategoryService.createCategory(category);
        return null;

    }

    public Map updateCategory(AdminCategoryInfo.CategoryRegisterV2 from) {
        return null;
    }

    public Map removeCategory(Long categoryId) {
        return null;
    }
}
