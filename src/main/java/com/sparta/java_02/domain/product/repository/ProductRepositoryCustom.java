package com.sparta.java_02.domain.product.repository;

import com.sparta.java_02.domain.category.dto.CategoryStatsDTO;
import com.sparta.java_02.domain.product.dto.CategoryProductDTO;
import com.sparta.java_02.domain.product.dto.ProductSearchCondition;
import com.sparta.java_02.domain.product.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    // 실습 1: Join과 @QueryProjection 활용
    List<CategoryProductDTO> findProductsByCategory();

    // 실습 2: 집계 함수와 groupBy 활용
    List<CategoryStatsDTO> findCategoryStats();

    // 실습 3: 동적 쿼리
    List<Product> searchProducts(ProductSearchCondition condition);
}