package com.sparta.java2.project.part1.commerce.domain.product.repository;

import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import com.sparta.java2.project.part1.commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    boolean existsByCategory_Id(Long categoryId);
}