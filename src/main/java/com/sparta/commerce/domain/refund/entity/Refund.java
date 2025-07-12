package com.sparta.commerce.domain.refund.entity;

import com.sparta.commerce.common.enums.PurchaseStatus;
import com.sparta.commerce.common.enums.RefundStatus;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.purchase.entity.Purchase;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

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
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase; // 환불 주문 정보

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product; // 환불 상품 정보

  @Column(nullable = false)
  BigDecimal refundAmount; // 환불 금액

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status; // 환불 상태

  @Column
  String reason; // 환불 사유

  @Column
  LocalDateTime requestedAt; // 환불 요청 시간

  @Column
  LocalDateTime processedAt; // 환불 처리 시간

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  /**
   * 환불 상태를 변경하는 메서드
   * @param newStatus 새로운 환불 상태
   */
  public void updateStatus(RefundStatus newStatus) {
    if (!ObjectUtils.isEmpty(newStatus)) {
      this.status = newStatus;
      // 환불 완료 시 processedAt 설정 (비즈니스 로직에 따라)
      if (newStatus == RefundStatus.COMPLETED) {
        this.processedAt = LocalDateTime.now();
      }
    }
  }

  /**
   * 환불 사유를 변경하는 메서드
   * @param newReason 새로운 환불 사유
   */
  public void updateReason(String newReason) {
    if (!ObjectUtils.isEmpty(newReason) && !newReason.trim().isEmpty()) {
      this.reason = newReason;
    }
  }
}
