package com.moveuk.ecommerce.application.admin.product.response;

public class AdminProductResult {
    public record ProductRegisterV1(Long productId){
    }
    public record ProductRegisterV2(Long productId, String name, String description, Integer price, Integer stock, Long categoryId){

    }
}
