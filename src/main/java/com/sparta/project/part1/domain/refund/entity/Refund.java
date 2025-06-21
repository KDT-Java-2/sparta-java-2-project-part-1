package com.sparta.project.part1.domain.refund.entity;

import com.sparta.project.part1.common.enums.RefundStatus;
import com.sparta.project.part1.domain.purchase.entity.Purchase;
import com.sparta.project.part1.domain.purchase.entity.PurchaseProduct;
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
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_product_id", nullable = false)
  PurchaseProduct purchaseProduct;

  @Column
  String reason;

  @Column
  Integer quantity;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;
}
