package com.sparta.project1.domain.product.repository;

import com.sparta.project1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
