package com.sparta.bootcamp.java_2_example.domain.refund.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.bootcamp.java_2_example.common.enums.PurchaseStatus;
import com.sparta.bootcamp.java_2_example.common.enums.RefundStatus;
import com.sparta.bootcamp.java_2_example.domain.purchase.entity.Purchase;
import com.sparta.bootcamp.java_2_example.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @Column(nullable = false)
  String reason;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

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
}
