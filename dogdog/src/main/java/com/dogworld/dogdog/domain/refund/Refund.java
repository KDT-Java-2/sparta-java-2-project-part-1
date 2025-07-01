package com.dogworld.dogdog.domain.refund;

import com.dogworld.dogdog.domain.BaseEntity;
import com.dogworld.dogdog.domain.purchaseproduct.PurchaseProduct;
import jakarta.persistence.Access;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RefundType refundType;

  private String reason;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RefundStatus status;

  private LocalDateTime requestedAt;

  private LocalDateTime processedAt;

  @Builder
  public Refund(RefundType refundType, String reason, RefundStatus status,
      LocalDateTime requestedAt,
      LocalDateTime processedAt) {
    this.refundType = refundType;
    this.reason = reason;
    this.status = status;
    this.requestedAt = requestedAt;
    this.processedAt = processedAt;
  }
}
