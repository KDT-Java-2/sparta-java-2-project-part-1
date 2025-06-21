package com.scb.project.commerce.domain.purchase.entity;

import com.scb.project.commerce.common.enums.PaymentMethod;
import com.scb.project.commerce.common.enums.PurchaseStatus;
import com.scb.project.commerce.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.ObjectUtils;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;  // 사용자 ID

    @Column(nullable = false)
    BigDecimal totalPrice;  // 주문 금액

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    PaymentMethod paymentMethod;    // 결제 방식

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    PurchaseStatus status;  // 주문 상태

    @Column(nullable = false)
    String shippingAddress; // 배송지

    @Column
    String memo;    // 배송 메모

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public Purchase(
        User user,
        BigDecimal totalPrice,
        PaymentMethod paymentMethod,
        PurchaseStatus status,
        String shippingAddress,
        String memo
    ) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.memo = memo;
    }

    public void setStatus(PurchaseStatus status) {
        if (!ObjectUtils.isEmpty(status)) {
            this.status = status;
        }
    }
}
