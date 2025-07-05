package com.js.commerce.domain.product.repository;

import com.js.commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByName(String name);
  
}
