package com.example.shoppingmall.domain.purchase.repository;

import com.example.shoppingmall.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
} 