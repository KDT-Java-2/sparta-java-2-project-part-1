package com.moveuk.ecommerce.infra.product;

import com.moveuk.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
