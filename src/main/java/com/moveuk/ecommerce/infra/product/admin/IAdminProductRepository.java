package com.moveuk.ecommerce.infra.product.admin;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.admin.AdminProductRepository;
import com.moveuk.ecommerce.infra.product.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class IAdminProductRepository implements AdminProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Optional<Product> findById(Long productId) {
        return jpaProductRepository.findById(productId);
    }
}
