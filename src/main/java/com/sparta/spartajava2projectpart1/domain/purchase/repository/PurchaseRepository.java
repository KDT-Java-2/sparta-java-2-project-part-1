package com.sparta.spartajava2projectpart1.domain.purchase.repository;

import com.sparta.spartajava2projectpart1.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
