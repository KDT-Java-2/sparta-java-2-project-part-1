package com.moveuk.ecommerce.infra.product;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import com.moveuk.ecommerce.domain.product.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IProductRepository implements ProductRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final JpaProductRepository jpaProductRepository;
    private final JpaProductInventoryRepository jpaProductInventoryRepository;

    @Override
    public Page<Product> searchProducts(Long id, Integer minPrice, Integer maxPrice, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ProductInventory> getProductInventory(long productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return Optional.empty();
    }
}
