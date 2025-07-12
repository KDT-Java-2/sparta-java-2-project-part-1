package com.moveuk.ecommerce.application.admin.product.request;

public class AdminProductInfo {
    public record ProductRegisterV1(String name, String description, Integer price, Integer stock, Long categoryId){

    }
    public record ProductRegisterV2(Long productId, String name, String description, Integer price, Integer stock, Long categoryId){

    }
}
