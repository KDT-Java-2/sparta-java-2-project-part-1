package com.scb.project.commerce.domain.product.repository;

import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 상품명 중복 여부를 확인합니다.
     *
     * @param productName 확인할 상품명
     * @return 해당 상품명이 이미 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByProductName(String productName);

    /**
     * 해당 카테고리에 하나 이상의 상품이 등록되어 있는지를 확인합니다.
     *
     * @param category 검사할 카테고리 엔티티
     * @return 상품이 하나 이상 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByCategory(Category category);
}
