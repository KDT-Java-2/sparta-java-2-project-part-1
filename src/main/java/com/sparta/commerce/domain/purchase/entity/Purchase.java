package com.sparta.commerce.domain.purchase.entity;

import com.sparta.commerce.common.enums.PaymentType;
import com.sparta.commerce.common.enums.PurchaseStatus;
import com.sparta.commerce.domain.user.entity.User;
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
import org.apache.commons.lang3.ObjectUtils;
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

  @Builder
  public Purchase(
      User user,
      BigDecimal total_price,
      PurchaseStatus status,
      String address,
      PaymentType paymentType,
      String tracking_number) {
    this.user = user;
    this.total_price = total_price;
    this.status = status;
    this.address = address;
    this.paymentType = paymentType;
    this.tracking_number = tracking_number;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user; // 구매한 유저

  @Column(nullable = false)
  BigDecimal total_price; // 주문 총합

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  PurchaseStatus status; // 구매 상태

  @Column
  String tracking_number; // 배송 추적 번호

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  PaymentType paymentType; // 결제 방식

  @Column
  String address; // 배송 주소

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  /**
   * 주문 상태를 변경하는 메서드
   * @param newStatus 새로운 주문 상태
   */
  public void updateStatus(PurchaseStatus newStatus) {
    if (!ObjectUtils.isEmpty(newStatus)) {
      this.status = newStatus;
    }
  }

  /**
   * 배송 주소를 업데이트하는 메서드
   * @param newAddress 새로운 배송 주소
   */
  public void updateAddress(String newAddress) {
    if (!ObjectUtils.isEmpty(newAddress) && !newAddress.trim().isEmpty()) {
      if(this.status != PurchaseStatus.PENDING) {
        throw new IllegalStateException("배송 주소는 주문이 대기 상태일 때만 변경할 수 있습니다.");
      }
      this.address = newAddress;
    }
  }

  /**
   * 운송장 번호를 설정하는 메서드
   * @param newTrackingNumber 새로운 운송장 번호
   */
  public void updateTrackingNumber(String newTrackingNumber) {
    if (!ObjectUtils.isEmpty(newTrackingNumber) && !newTrackingNumber.trim().isEmpty()) {
      this.tracking_number = newTrackingNumber;
    }
  }
}
