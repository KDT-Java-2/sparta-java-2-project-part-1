package com.moveuk.ecommerce.infra.product.admin;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import com.moveuk.ecommerce.domain.product.admin.AdminProductRepository;
import com.moveuk.ecommerce.infra.product.JpaProductInventoryRepository;
import com.moveuk.ecommerce.infra.product.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class IAdminProductRepository implements AdminProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final JpaProductInventoryRepository jpaProductInventoryRepository;

    @Override
    public Optional<Product> findById(Long productId) {
        return jpaProductRepository.findById(productId);
    }

    @Override
    public void createProduct(Product product) {
        jpaProductRepository.save(product);
    }

    @Override
    public void createProductInventory(ProductInventory productInventory) {
        jpaProductInventoryRepository.save(productInventory);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaProductRepository.findByName(name);
    }

    @Override
    public void updateProduct(Product product) {
        jpaProductRepository.save(product);
    }

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        jpaProductInventoryRepository.save(productInventory);
    }

    @Override
    public boolean existsByCategoryId(long categoryId) {
        return jpaProductRepository.existsByCategoryId(categoryId);
    }
}
