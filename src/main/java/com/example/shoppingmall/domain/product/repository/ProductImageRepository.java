package com.example.shoppingmall.domain.product.repository;

import com.example.shoppingmall.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
} 