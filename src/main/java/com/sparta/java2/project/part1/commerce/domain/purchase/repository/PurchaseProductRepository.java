package com.sparta.java2.project.part1.commerce.domain.purchase.repository;

import com.sparta.java2.project.part1.commerce.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
    List<PurchaseProduct> findAllByProduct_Id(Long productId);
}