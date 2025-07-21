package com.sparta.e_project.domain.product.repository.admin;

import com.sparta.e_project.domain.category.entity.Category;
import com.sparta.e_project.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProductRepository extends JpaRepository<Product, Long> {

  boolean existsByName(String name);

  boolean existsByCategory(Category category);
}
