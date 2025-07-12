package com.moveuk.ecommerce.application.product.response;

import com.moveuk.ecommerce.domain.product.Product;

import java.math.RoundingMode;

public class ProductResult {
    public record ProductRegisterV1(Long id, String name, long price, String description, long stock){
        public static ProductRegisterV1 from(Product product, long stock) {
            return new ProductRegisterV1(
                    product.getId(),
                    product.getName(),
                    product.getPrice().setScale(0, RoundingMode.DOWN).longValue(),
                    product.getDescription(),
                    stock
            );
        }
    }
}
