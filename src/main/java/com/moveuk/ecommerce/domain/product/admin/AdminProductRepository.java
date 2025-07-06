package com.moveuk.ecommerce.domain.product.admin;

import com.moveuk.ecommerce.domain.product.Product;

import java.util.Optional;

public interface AdminProductRepository {
    Optional<Product> findById(Long productId);
}
