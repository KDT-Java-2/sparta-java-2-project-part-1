package com.sparta.ecommerce.domain.refund.entity;

import com.sparta.ecommerce.common.enums.RefundStatus;
import com.sparta.ecommerce.domain.product.entity.Product;
import com.sparta.ecommerce.domain.purchase.entity.Purchase;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  Product product;

  @Column
  Integer quantity;

  @Column(nullable = false)
  String reason;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  RefundStatus refundStatus;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

  @Builder
  public Refund(Purchase purchase, Product product, Integer quantity, String reason,
      RefundStatus refundStatus) {
    this.purchase = purchase;
    this.product = product;
    this.quantity = quantity;
    this.reason = reason;
    this.refundStatus = refundStatus;
  }
}
