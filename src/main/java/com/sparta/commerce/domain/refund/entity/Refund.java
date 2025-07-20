package com.sparta.commerce.domain.refund.entity;

import com.sparta.commerce.common.enums.RefundStatus;
import com.sparta.commerce.domain.purchase.entity.PurchaseProduct;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_product_id", nullable = false)
  private PurchaseProduct purchaseProduct;

  @Column(name = "refund_reason")
  private String refundReason;

  @Enumerated(EnumType.STRING)
  @Column(name = "refund_status", nullable = false, length = 20)
  private RefundStatus refundStatus;

  @CreationTimestamp
  @Column(name = "requested_at", updatable = false)
  private LocalDateTime requestedAt;

  @Column(name = "processed_at")
  private LocalDateTime processedAt;

  @Builder
  public Refund(PurchaseProduct purchaseProduct, String refundReason, RefundStatus refundStatus) {
    this.purchaseProduct = purchaseProduct;
    this.refundReason = refundReason;
    this.refundStatus = refundStatus;
  }
}
