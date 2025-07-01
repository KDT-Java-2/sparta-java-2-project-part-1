package com.java_project.part_1.domain.product.repository;

import com.java_project.part_1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByCategory_Id(Long categoryId);

  boolean existsByName(String name);
}
