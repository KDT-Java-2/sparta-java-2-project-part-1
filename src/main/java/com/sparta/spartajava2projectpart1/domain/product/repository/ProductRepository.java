package com.sparta.spartajava2projectpart1.domain.product.repository;

import com.sparta.spartajava2projectpart1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
