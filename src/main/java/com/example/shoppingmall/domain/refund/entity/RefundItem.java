package com.example.shoppingmall.domain.refund.entity;

import com.example.shoppingmall.domain.purchase.entity.PurchaseProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "refund_item")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Refund refund;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    PurchaseProduct purchaseProduct;

    @Column(nullable = false, columnDefinition = "int default 1")
    Integer quantity = 1;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal refundAmount;

    @Column(length = 500)
    String reason; // 개별 아이템 환불 사유

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Builder
    public RefundItem(
            Refund refund,
            PurchaseProduct purchaseProduct,
            Integer quantity,
            BigDecimal refundAmount,
            String reason
    ) {
        this.refund = refund;
        this.purchaseProduct = purchaseProduct;
        this.quantity = quantity;
        this.refundAmount = refundAmount;
        this.reason = reason;
    }

    // 연관관계 편의 메서드
    public void setRefund(Refund refund) {
        this.refund = refund;
    }
} 