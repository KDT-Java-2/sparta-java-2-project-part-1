package com.moveuk.ecommerce.inferfaces.admin.product.response;

import com.moveuk.ecommerce.application.admin.product.response.AdminProductResult;

public class AdminProductResponse{
    public record ProductResponseV1(Long productId){
        public static ProductResponseV1 of(AdminProductResult.ProductRegisterV1 productRegisterV1) {
            return new ProductResponseV1(productRegisterV1.productId());
        }
    }
    public record ProductResponseV2(Long productId, String name, String description, Integer price, Integer stock, Long categoryId){
        public static ProductResponseV2 of(AdminProductResult.ProductRegisterV2 productRegisterV2) {
            return new ProductResponseV2(
                    productRegisterV2.productId()
                    , productRegisterV2.name()
                    , productRegisterV2.description()
                    , productRegisterV2.price()
                    , productRegisterV2.stock()
                    , productRegisterV2.categoryId()
            );
        }
    }

    public record ProductResponseV3() {
    }
}
