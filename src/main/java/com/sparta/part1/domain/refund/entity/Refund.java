package com.sparta.part1.domain.refund.entity;

import com.sparta.part1.domain.purchase.purchase.Purchase;
import com.sparta.part1.domain.purchase_product.entity.PurchaseProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "refund")
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  Integer quantity;
  BigDecimal refundAmount;
  String reason;
  String status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id")
  Purchase purchase;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_product_id")
  PurchaseProduct purchaseProduct;

  public void setReason(String reason) {
    if (StringUtils.hasText(reason)) {
      this.reason = reason;
    }
  }

  public void setStatus(String status) {
    if (StringUtils.hasText(status)) {
      this.status = status;
    }
  }
}
