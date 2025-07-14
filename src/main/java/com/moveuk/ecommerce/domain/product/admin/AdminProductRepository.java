package com.moveuk.ecommerce.domain.product.admin;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;

import java.util.Optional;

public interface AdminProductRepository {
    Optional<Product> findById(Long productId);

    void createProduct(Product product);

    void createProductInventory(ProductInventory productInventory);

    boolean existsByName(String name);

    void updateProduct(Product product);

    void updateProductInventory(ProductInventory productInventory);

    boolean existsByCategoryId(long categoryId);
}
