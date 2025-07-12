package com.moveuk.ecommerce.application.admin.category.request;

public class AdminCategoryInfo {
    public record CategoryRegisterV1(String name, String description, Long parentId){}

    public record CategoryRegisterV2(Long categoryId, String name, String description, Long parentId){}
}
