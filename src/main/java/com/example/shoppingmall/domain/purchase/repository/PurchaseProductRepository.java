package com.example.shoppingmall.domain.purchase.repository;

import com.example.shoppingmall.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
} 