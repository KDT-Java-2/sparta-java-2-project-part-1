package com.sparta.ecommerce.domain.purchase.repository;

import com.sparta.ecommerce.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  @Query("""
          SELECT COUNT(p) > 0
          FROM Purchase p
          JOIN PurchaseProduct pp
          on p = pp.purchase
          WHERE pp.product.id = :productId
      """)
  boolean existsProductInCompletedPurchase(@Param("productId") Long productId);
}
