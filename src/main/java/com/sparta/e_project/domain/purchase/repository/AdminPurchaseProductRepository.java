package com.sparta.e_project.domain.purchase.repository;

import com.sparta.e_project.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminPurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

  @Query(
      "SELECT COUNT(*) >0  FROM Product p JOIN PurchaseProduct pp ON p.id = pp.product.id WHERE pp.product.id = :productId AND pp.purchase.status ='COMPLETED'"
  )
  boolean existsByProductId(Long productId);
}
