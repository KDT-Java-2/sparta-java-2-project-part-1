package com.sparta.java_02.domain.refund.entity;

import com.sparta.java_02.common.enums.RefundStatus;
import com.sparta.java_02.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class Refund {


  @Id
  @Column(name = "purchase_id")
  private Long purchaseId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "purchase_id")
  private Purchase purchase;

  @Column(name = "reason_cd", nullable = false, length = 50)
  private String reasonCd;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private RefundStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @Builder
  public Refund(Purchase purchase, String reasonCd, RefundStatus status) {
    this.purchase = purchase;
    this.reasonCd = reasonCd;
    this.status = status;
  }

  // setter
  public void setStatus(RefundStatus status) {
    this.status = status;
  }

}
