package com.moveuk.ecommerce.inferfaces.admin.product.reqest;

import com.moveuk.ecommerce.application.admin.product.request.AdminProductInfo;

public class AdminProductRequest {
    public record ProductRegisterV1(String name, String description, Integer price, Integer stock, Long categoryId){
        public static AdminProductInfo.ProductRegisterV1 from(ProductRegisterV1 productRegisterV1){
            return new AdminProductInfo.ProductRegisterV1(productRegisterV1.name(), productRegisterV1.description(), productRegisterV1.price(), productRegisterV1.stock(), productRegisterV1.categoryId());
        }

        public static AdminProductInfo.ProductRegisterV2 from(Long productId, ProductRegisterV1 productRegisterV2){
            return new AdminProductInfo.ProductRegisterV2(productId, productRegisterV2.name(), productRegisterV2.description(), productRegisterV2.price(), productRegisterV2.stock(), productRegisterV2.categoryId());
        }
    }

}
