package com.moveuk.ecommerce.inferfaces.admin.category.request;

import com.moveuk.ecommerce.application.admin.category.request.AdminCategoryInfo;

public class AdminCategoryRequest {
    public record CategoryRegisterV1(String name, String description, Long parentId){
        public static AdminCategoryInfo.CategoryRegisterV1 from(CategoryRegisterV1 categoryRegisterV1){
            return new AdminCategoryInfo.CategoryRegisterV1(categoryRegisterV1.name(), categoryRegisterV1.description(), categoryRegisterV1.parentId());
        }

        public static AdminCategoryInfo.CategoryRegisterV2 from(Long categoryId, CategoryRegisterV1 categoryRegisterV1){
            return new AdminCategoryInfo.CategoryRegisterV2(categoryId, categoryRegisterV1.name(), categoryRegisterV1.description(), categoryRegisterV1.parentId());
        }
    }
}
