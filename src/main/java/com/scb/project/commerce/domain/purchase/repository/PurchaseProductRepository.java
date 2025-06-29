package com.scb.project.commerce.domain.purchase.repository;

import com.scb.project.commerce.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

}
