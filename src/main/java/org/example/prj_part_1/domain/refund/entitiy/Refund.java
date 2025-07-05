package org.example.prj_part_1.domain.refund.entitiy;

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
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.prj_part_1.common.enums.RefundStatus;
import org.example.prj_part_1.domain.purchase.entity.PurchaseProduct;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_product_id", nullable = false)
  PurchaseProduct purchaseProduct;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "purchase_id", nullable = false)
//  Purchase purchase;

  @Column(length = 500)
  String reason; // 환불 사유

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  RefundStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

}
