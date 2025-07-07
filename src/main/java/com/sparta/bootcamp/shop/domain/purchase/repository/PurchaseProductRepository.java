package com.sparta.bootcamp.shop.domain.purchase.repository;

import java.util.List;

import com.sparta.bootcamp.shop.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

  List<PurchaseProduct> findByPurchase_Id(Long purchaseId);

}