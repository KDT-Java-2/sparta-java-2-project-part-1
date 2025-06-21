package com.sparta.java_02.domain.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    /**
     * 특정 사용자의 구매 내역 조회 (최신순)
     */
    @Query("SELECT p FROM Purchase p JOIN FETCH p.product WHERE p.user.id = :userId ORDER BY p.createdAt DESC")
    List<Purchase> findByUserIdWithProductOrderByCreatedAtDesc(@Param("userId") Long userId);

    /**
     * 특정 상품의 구매 내역 조회
     */
    @Query("SELECT p FROM Purchase p JOIN FETCH p.user WHERE p.product.id = :productId ORDER BY p.createdAt DESC")
    List<Purchase> findByProductIdWithUserOrderByCreatedAtDesc(@Param("productId") Long productId);

    /**
     * 상태별 구매 내역 조회
     */
    List<Purchase> findByStatusOrderByCreatedAtDesc(PurchaseStatus status);

    /**
     * 특정 사용자의 특정 상태 구매 내역 조회
     */
    List<Purchase> findByUserIdAndStatusOrderByCreatedAtDesc(Long userId, PurchaseStatus status);

    /**
     * 기간별 구매 내역 조회
     */
    @Query("SELECT p FROM Purchase p WHERE p.createdAt BETWEEN :startDate AND :endDate ORDER BY p.createdAt DESC")
    List<Purchase> findByCreatedAtBetweenOrderByCreatedAtDesc(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );

    /**
     * 환불 가능한 구매 내역 조회 (환불 요청이 없는 완료된 구매)
     */
    @Query("SELECT p FROM Purchase p WHERE p.user.id = :userId AND p.status IN ('DELIVERED', 'COMPLETED') AND p.refund IS NULL")
    List<Purchase> findRefundablePurchasesByUserId(@Param("userId") Long userId);

    /**
     * 특정 사용자의 구매 건수 조회
     */
    long countByUserId(Long userId);

    /**
     * 특정 상품의 판매 건수 조회
     */
    long countByProductId(Long productId);
} 