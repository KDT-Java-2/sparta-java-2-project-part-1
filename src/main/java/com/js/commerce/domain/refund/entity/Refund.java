package com.js.commerce.domain.refund.entity;

import com.js.commerce.common.enums.RefundStatus;
import com.js.commerce.domain.purchase.entity.Purchase;
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
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

@Entity
@Table
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @Column(length = 255)
  String reason; // 환불 사유

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime requestedAt; // 요청 시각

  @Column
  LocalDateTime processedAt; // 처리 시각

  @Builder
  public Refund(
      Purchase purchase,
      String reason,
      RefundStatus status
  ) {
    this.purchase = purchase;
    this.reason = reason;
    this.status = status;
  }

  public void setStatus(RefundStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

  /**
   * 환불 승인
   **/
  public void approve() {
    this.status = RefundStatus.APPROVED;
    this.processedAt = LocalDateTime.now();
  }

  /**
   * 환불 거절
   **/
  public void reject() {
    this.status = RefundStatus.REJECTED;
    this.processedAt = LocalDateTime.now();
  }

  /**
   * 환불 완료
   **/
  public void complete() {
    this.status = RefundStatus.COMPLETED;
    this.processedAt = LocalDateTime.now();
  }
}
