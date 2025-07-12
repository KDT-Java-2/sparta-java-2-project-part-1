package com.moveuk.ecommerce.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository {
    Page<Product> searchProducts(Long id, Integer minPrice, Integer maxPrice, Pageable pageable);
    Optional<ProductInventory> getProductInventory(long productId);
    Optional<Product> getProduct(long productId);
}
