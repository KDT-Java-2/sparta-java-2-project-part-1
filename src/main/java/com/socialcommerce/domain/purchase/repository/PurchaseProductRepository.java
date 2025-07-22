package com.socialcommerce.domain.purchase.repository;

import com.socialcommerce.common.enums.PurchaseStatus;
import com.socialcommerce.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

  boolean existsByProductIdAndPurchaseStatus(Long productId, PurchaseStatus status);

//  @Query("SELECT CASE WHEN COUNT(pp) > 0 THEN true ELSE false END " +
//      "FROM PurchaseProduct pp " +
//      "JOIN pp.purchase p " +
//      "WHERE pp.product.id = :productId AND p.status = :status")
//  boolean existsCompletedProduct(@Param("productId") Long productId, @Param("status") PurchaseStatus status);

}
