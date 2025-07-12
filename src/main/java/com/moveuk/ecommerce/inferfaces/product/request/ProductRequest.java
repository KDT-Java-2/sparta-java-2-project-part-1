package com.moveuk.ecommerce.inferfaces.product.request;

import com.moveuk.ecommerce.application.product.request.ProductInfo;

public class ProductRequest {
    public record ProductRegisterV1(Long categoryId, Integer minPrice, Integer maxPrice
            , Integer page,Integer size, String sortBy) {
        public static ProductInfo.ProductRegisterV1 from(ProductRegisterV1 productRegisterV1) {
            return new ProductInfo.ProductRegisterV1(productRegisterV1.categoryId(), productRegisterV1.minPrice(), productRegisterV1.maxPrice()
                    , productRegisterV1.page(), productRegisterV1.size(), productRegisterV1.sortBy());
        }

    }

    public record ProductRegisterV2(long productId) {
        public static ProductInfo.ProductRegisterV2 from(long productId) {
            return new ProductInfo.ProductRegisterV2(productId);
        }
    }
}
