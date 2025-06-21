package com.sparta.javamarket.domain.purchase_product.repository;

import com.sparta.javamarket.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


}
