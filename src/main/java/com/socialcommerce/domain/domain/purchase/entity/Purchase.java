package com.socialcommerce.domain.domain.purchase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.socialcommerce.domain.common.enums.PurchaseStatus;
import com.socialcommerce.domain.domain.refund.entity.Refund;
import com.socialcommerce.domain.domain.user.entity.User;
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

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase { // 주문

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  User user;

  // 소수점 등 정확한 숫자로 할때 사용하기 좋다.
  @Column
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)  // 실제 들어갈때 Enum 에 있는 글자 그대로 들어감
  @Column(nullable = false, length = 20)
  PurchaseStatus status;  // 실제 컬럼상에는 VARCHAR(20)

  @Column(nullable = false, columnDefinition = "TEXT")
  String shippingAddress;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status, String shippingAddress) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
    this.shippingAddress = shippingAddress;
  }

  public void setStatus(PurchaseStatus status) {
    if (ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

}
