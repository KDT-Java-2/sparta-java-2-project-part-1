package com.sparta.java_02.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 상품명으로 검색 (부분 일치)
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * 가격 범위로 상품 검색
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * 재고가 있는 상품만 조회
     */
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0")
    List<Product> findAvailableProducts();

    /**
     * 재고가 부족한 상품 조회
     */
    @Query("SELECT p FROM Product p WHERE p.stockQuantity < :threshold")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
} 