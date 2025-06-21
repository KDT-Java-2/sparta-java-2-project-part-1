package com.sparta.sparta_java_2_project_part_1.domain.refund.entity;

import com.sparta.sparta_java_2_project_part_1.domain.purchase.entity.Purchase;
import com.sparta.sparta_java_2_project_part_1.enums.RefundStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refunds {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToOne
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @Lob
  @Column(nullable = true)
  String reason;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Refunds(Purchase purchase, String reason, RefundStatus status) {
    this.purchase = purchase;
    this.reason = reason;
    this.status = status;
  }

  public void updateStatus(RefundStatus status) {
    this.status = status;
  }

  public void updateReason(String reason) {
    this.reason = reason;
  }

  public void updatePurchase(Purchase purchase) {
    this.purchase = purchase;
  }

  public void updateRefunds(Purchase purchase, String reason, RefundStatus status) {
    this.purchase = purchase;
    this.reason = reason;
    this.status = status;
  }



}
