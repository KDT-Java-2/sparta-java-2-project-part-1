package com.sparta.java2.project.part1.commerce.domain.purchase.repository;

import com.sparta.java2.project.part1.commerce.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}