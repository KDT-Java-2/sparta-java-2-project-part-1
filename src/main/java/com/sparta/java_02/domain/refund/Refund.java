package com.sparta.java_02.domain.refund;

import com.sparta.java_02.domain.purchase.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false, unique = true)
    private Purchase purchase;

    @Column(nullable = false, length = 500)
    private String reason;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal refundAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RefundStatus status;

    @Column(columnDefinition = "TEXT")
    private String adminNotes;

    @Column
    private Long processedBy;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    @Column
    private LocalDateTime processedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Refund(Purchase purchase, String reason, BigDecimal refundAmount) {
        validateRefundData(reason, refundAmount);
        this.purchase = purchase;
        this.reason = reason;
        this.refundAmount = refundAmount;
        this.status = RefundStatus.REQUESTED;
        this.requestedAt = LocalDateTime.now();
    }

    public void updateStatus(RefundStatus newStatus, Long processedBy, String adminNotes) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                String.format("상태를 %s에서 %s로 변경할 수 없습니다.", 
                    this.status.getDisplayName(), newStatus.getDisplayName())
            );
        }
        
        this.status = newStatus;
        this.processedBy = processedBy;
        this.adminNotes = adminNotes;
        
        if (newStatus.isFinalStatus()) {
            this.processedAt = LocalDateTime.now();
        }
    }

    public void approve(Long adminId, String notes) {
        updateStatus(RefundStatus.APPROVED, adminId, notes);
    }

    public void reject(Long adminId, String notes) {
        updateStatus(RefundStatus.REJECTED, adminId, notes);
    }

    public void complete(Long adminId, String notes) {
        updateStatus(RefundStatus.COMPLETED, adminId, notes);
    }

    public void cancel() {
        if (this.status.isFinalStatus()) {
            throw new IllegalStateException("최종 상태에서는 취소할 수 없습니다: " + this.status.getDisplayName());
        }
        this.status = RefundStatus.CANCELED;
        this.processedAt = LocalDateTime.now();
    }

    public boolean isProcessable() {
        return this.status == RefundStatus.APPROVED;
    }

    private void validateRefundData(String reason, BigDecimal refundAmount) {
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("환불 사유는 필수입니다.");
        }
        if (refundAmount == null || refundAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("환불 금액은 0보다 커야 합니다.");
        }
    }
} 