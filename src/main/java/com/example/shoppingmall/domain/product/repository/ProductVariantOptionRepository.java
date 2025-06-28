package com.example.shoppingmall.domain.product.repository;

import com.example.shoppingmall.domain.product.entity.ProductVariantOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantOptionRepository extends JpaRepository<ProductVariantOption, Long> {
} 