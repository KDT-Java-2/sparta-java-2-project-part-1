package com.example.shoppingmall.domain.refund.entity;

import com.example.shoppingmall.common.enums.RefundStatus;
import com.example.shoppingmall.domain.purchase.entity.Purchase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "refund")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    Purchase purchase;

    @Column(nullable = false, length = 500)
    String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    RefundStatus status;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal refundAmount;

    @OneToMany(mappedBy = "refund", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<RefundItem> refundItems = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public Refund(
            Purchase purchase,
            String reason,
            RefundStatus status,
            BigDecimal refundAmount
    ) {
        this.purchase = purchase;
        this.reason = reason;
        this.status = status;
        this.refundAmount = refundAmount;
    }

    public void updateStatus(RefundStatus status) {
        this.status = status;
    }

    // 환불 아이템 추가
    public void addRefundItem(RefundItem refundItem) {
        this.refundItems.add(refundItem);
        refundItem.setRefund(this);
    }

    // 환불 아이템 제거
    public void removeRefundItem(RefundItem refundItem) {
        this.refundItems.remove(refundItem);
        refundItem.setRefund(null);
    }

    // 총 환불 금액 계산
    public BigDecimal calculateTotalRefundAmount() {
        return this.refundItems.stream()
                .map(RefundItem::getRefundAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
} 