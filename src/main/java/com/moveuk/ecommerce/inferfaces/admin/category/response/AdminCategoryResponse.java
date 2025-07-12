package com.moveuk.ecommerce.inferfaces.admin.category.response;

import com.moveuk.ecommerce.application.admin.category.response.AdminCategoryResult;

import java.util.List;

public class AdminCategoryResponse {

    public record CategoryResponseV1 (Long categoryId){
        public static AdminCategoryResponse.CategoryResponseV1 of(AdminCategoryResult.CategoryRegisterV1 categoryRegisterV1) {
            return new AdminCategoryResponse.CategoryResponseV1(
                    categoryRegisterV1.categoryId()
            );
        }
    }
    public record AdminCategoryTreeResponse(Long id, String name, List<AdminCategoryTreeResponse> children) {
        public static AdminCategoryTreeResponse of(AdminCategoryResult.CategoryTreeDto category) {
            return new AdminCategoryTreeResponse(
                    category.id(),
                    category.name(),
                    category.children().stream()
                            .map(AdminCategoryTreeResponse::of)
                            .toList()
            );
        }

        public static List<AdminCategoryTreeResponse> of(List<AdminCategoryResult.CategoryTreeDto> dtos) {
            return dtos.stream()
                    .map(AdminCategoryTreeResponse::of)
                    .toList();
            }
    }

}
