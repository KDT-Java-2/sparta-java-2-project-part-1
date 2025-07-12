package com.moveuk.ecommerce.application.product.request;

public class ProductInfo {
    public record ProductRegisterV1(Long categoryId, Integer minPrice, Integer maxPrice
            , Integer page,Integer size, String sortBy){}

    public record ProductRegisterV2(Long productId){}
}
