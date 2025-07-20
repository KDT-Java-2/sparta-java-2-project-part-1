package com.example.shoppingmall.domain.purchase.repository;

import com.example.shoppingmall.common.enums.PurchaseStatus;
import com.example.shoppingmall.domain.purchase.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
    
    /**
     * 특정 상품이 완료된 주문에 포함되어 있는지 확인
     * ProductVariant를 통해 Product와 연결되어 있으므로 JOIN으로 확인
     */
    @Query("SELECT COUNT(pp) > 0 FROM PurchaseProduct pp " +
           "JOIN pp.productVariant pv " +
           "JOIN pp.purchase p " +
           "WHERE pv.product.id = :productId AND p.status = :status")
    boolean existsByProductIdAndPurchaseStatus(@Param("productId") Long productId, 
                                             @Param("status") PurchaseStatus status);
} 