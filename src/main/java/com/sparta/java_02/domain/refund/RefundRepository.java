package com.sparta.java_02.domain.refund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

    /**
     * 구매 ID로 환불 조회
     */
    Optional<Refund> findByPurchaseId(Long purchaseId);

    /**
     * 상태별 환불 내역 조회
     */
    @Query("SELECT r FROM Refund r JOIN FETCH r.purchase p JOIN FETCH p.user WHERE r.status = :status ORDER BY r.requestedAt DESC")
    List<Refund> findByStatusWithPurchaseAndUserOrderByRequestedAtDesc(@Param("status") RefundStatus status);

    /**
     * 특정 사용자의 환불 내역 조회
     */
    @Query("SELECT r FROM Refund r JOIN FETCH r.purchase p WHERE p.user.id = :userId ORDER BY r.requestedAt DESC")
    List<Refund> findByPurchaseUserIdOrderByRequestedAtDesc(@Param("userId") Long userId);

    /**
     * 처리자별 환불 내역 조회
     */
    @Query("SELECT r FROM Refund r JOIN FETCH r.purchase p JOIN FETCH p.user WHERE r.processedBy = :processedBy ORDER BY r.processedAt DESC")
    List<Refund> findByProcessedByWithPurchaseAndUserOrderByProcessedAtDesc(@Param("processedBy") Long processedBy);

    /**
     * 기간별 환불 요청 조회
     */
    @Query("SELECT r FROM Refund r WHERE r.requestedAt BETWEEN :startDate AND :endDate ORDER BY r.requestedAt DESC")
    List<Refund> findByRequestedAtBetweenOrderByRequestedAtDesc(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );

    /**
     * 처리 대기 중인 환불 건수
     */
    @Query("SELECT COUNT(r) FROM Refund r WHERE r.status IN ('REQUESTED', 'UNDER_REVIEW', 'APPROVED')")
    long countPendingRefunds();

    /**
     * 특정 사용자의 환불 요청 건수
     */
    @Query("SELECT COUNT(r) FROM Refund r JOIN r.purchase p WHERE p.user.id = :userId")
    long countByPurchaseUserId(@Param("userId") Long userId);

    /**
     * 완료된 환불 총액 (특정 기간)
     */
    @Query("SELECT COALESCE(SUM(r.refundAmount), 0) FROM Refund r WHERE r.status = 'COMPLETED' AND r.processedAt BETWEEN :startDate AND :endDate")
    Long sumCompletedRefundAmountByPeriod(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );
} 