package com.dogworld.dogdog.product.domain.repository;

import com.dogworld.dogdog.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

  boolean existsByName(String name);

  boolean existsByCategoryId(Long categoryId);
}
