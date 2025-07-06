package com.sparta.javamarket.domain.product.repository;

import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  boolean existsByCategory(Category category);
}
