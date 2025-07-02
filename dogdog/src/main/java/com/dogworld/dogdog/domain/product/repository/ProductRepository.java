package com.dogworld.dogdog.domain.product.repository;

import com.dogworld.dogdog.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

  boolean existsByName(String name);

  boolean existsByCategoryId(Long categoryId);
}
