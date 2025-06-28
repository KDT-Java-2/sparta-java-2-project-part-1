package com.example.shoppingmall.domain.product.repository;

import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
} 