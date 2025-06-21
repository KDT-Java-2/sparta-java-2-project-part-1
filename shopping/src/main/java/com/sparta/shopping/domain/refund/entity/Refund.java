package com.sparta.shopping.domain.refund.entity;

import com.sparta.shopping.common.enums.PurchaseStatus;
import com.sparta.shopping.common.enums.RefundStatus;
import com.sparta.shopping.domain.product.entity.Product;
import com.sparta.shopping.domain.purchase.entity.Purchase;
import com.sparta.shopping.domain.user.entity.User;
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

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
  Long id;

  @Column(nullable = false, length = 500)
  String reason;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status; //환불상태

  @Column(nullable = false)
  BigDecimal refundAmount; //환불금액

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="purchase_id",nullable = false)
  Purchase purchase;

  @Column(name = "del_yn", nullable = false, length = 1)
  String delYn;

  @Column
  LocalDateTime deletedAt;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Refund(String reason, RefundStatus status, BigDecimal refundAmount, Purchase purchase) {
    this.reason = reason;
    this.status = status;
    this.refundAmount = refundAmount;
    this.purchase = purchase;
    this.delYn="N";
  }
}
