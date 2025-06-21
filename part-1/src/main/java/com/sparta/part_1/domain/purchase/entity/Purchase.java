package com.sparta.part_1.domain.purchase.entity;


import com.sparta.part_1.common.enums.DeliveryStatus;
import com.sparta.part_1.common.enums.PaymentMethod;
import com.sparta.part_1.common.enums.PurchaseStatus;
import com.sparta.part_1.domain.user.entity.User;
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

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  PurchaseStatus status;

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Column(nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  PaymentMethod paymentMethod;

  @Column(nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  DeliveryStatus deliveryStatus;

  @Column
  LocalDateTime payedAt;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Purchase(PurchaseStatus status, BigDecimal totalPrice, PaymentMethod paymentMethod,
      DeliveryStatus deliveryStatus, LocalDateTime payedAt) {
    this.status = status;
    this.totalPrice = totalPrice;
    this.paymentMethod = paymentMethod;
    this.deliveryStatus = deliveryStatus;
    this.payedAt = payedAt;
  }
}
