package com.sparta.java_02.domain.purchase;

import com.sparta.java_02.domain.product.Product;
import com.sparta.java_02.domain.refund.Refund;
import com.sparta.java_02.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PurchaseStatus status;

    @Column(length = 50)
    private String paymentMethod;

    @Column(columnDefinition = "TEXT")
    private String deliveryAddress;

    @Column(columnDefinition = "TEXT")
    private String orderNotes;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Refund refund;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Purchase(User user, Product product, Integer quantity, BigDecimal unitPrice, 
                   String paymentMethod, String deliveryAddress, String orderNotes) {
        validatePurchaseData(quantity, unitPrice);
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));
        this.status = PurchaseStatus.PENDING;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.orderNotes = orderNotes;
    }

    public void updateStatus(PurchaseStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                String.format("상태를 %s에서 %s로 변경할 수 없습니다.", 
                    this.status.getDisplayName(), newStatus.getDisplayName())
            );
        }
        this.status = newStatus;
    }

    public void cancel() {
        if (!this.status.isCancelable()) {
            throw new IllegalStateException("현재 상태에서는 취소할 수 없습니다: " + this.status.getDisplayName());
        }
        this.status = PurchaseStatus.CANCELED;
    }

    public boolean canRequestRefund() {
        return this.status.isRefundable() && this.refund == null;
    }

    public BigDecimal calculateRefundableAmount() {
        if (!canRequestRefund()) {
            return BigDecimal.ZERO;
        }
        return this.totalAmount;
    }

    private void validatePurchaseData(Integer quantity, BigDecimal unitPrice) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("단가는 0 이상이어야 합니다.");
        }
    }
} 