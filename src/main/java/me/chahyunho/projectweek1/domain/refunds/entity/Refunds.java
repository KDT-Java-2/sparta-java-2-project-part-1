package me.chahyunho.projectweek1.domain.refunds.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.chahyunho.projectweek1.domain.purchase.entity.Purchase;
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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @Column(nullable = false, length = 255)
  String reason;

  @Column(nullable = false, length = 20)
  String status; // REQUESTED, APPROVED, REJECTED, COMPLETED 등

  @Column(name = "refund_amount", precision = 10, scale = 2)
  BigDecimal refundAmount;

  @Column(length = 50)
  String method; // 카드, 계좌이체, 포인트 등

  @Column(name = "bank_account", length = 100)
  String bankAccount;

  @Column(name = "is_partial")
  Boolean isPartial = false;

  @Column(name = "refunded_by", length = 100)
  String refundedBy;

  @Column(name = "refunded_at")
  LocalDateTime refundedAt;

  @Lob
  @Column
  String note;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false, updatable = false)
  LocalDateTime updatedAt;

  @Builder
  public Refunds(Purchase purchase, String reason, String status, BigDecimal refundAmount,
      String method,
      String bankAccount, Boolean isPartial, String refundedBy, LocalDateTime refundedAt,
      String note) {
    this.purchase = purchase;
    this.reason = reason;
    this.status = status;
    this.refundAmount = refundAmount;
    this.method = method;
    this.bankAccount = bankAccount;
    this.isPartial = isPartial;
    this.refundedBy = refundedBy;
    this.refundedAt = refundedAt;
    this.note = note;
  }
}
