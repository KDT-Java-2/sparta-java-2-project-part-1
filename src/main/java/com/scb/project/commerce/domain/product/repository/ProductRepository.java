package com.scb.project.commerce.domain.product.repository;

import com.scb.project.commerce.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 상품명으로 상품 찾는 메서드
     *
     * @param productName 상품명
     * @return 상품 객체
     */
    Product findByProductName(String productName);


    /**
     * 브랜드의 상품을 모두 찾는 메서드
     *
     * @param brandName 브랜드명
     * @return 브랜드의 상품 객체 리스트
     */
    List<Product> findAllByBrandName(String brandName);

    /**
     * 해당 상품명이 존재하지는 찾는 메서드
     *
     * @param productName 상품명
     * @return 불리언 값 (true / false)
     */
    boolean existsByProductName(String productName);

    /**
     * 브랜드의 상품을 모두 삭제하는 메서드
     *
     * @param brandName 브랜드명
     */
    void deleteAllByBrandName(String brandName);
}
