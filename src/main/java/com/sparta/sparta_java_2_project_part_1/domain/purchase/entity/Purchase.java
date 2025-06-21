package com.sparta.sparta_java_2_project_part_1.domain.purchase.entity;

import com.sparta.sparta_java_2_project_part_1.domain.user.entity.User;
import com.sparta.sparta_java_2_project_part_1.enums.PurchaseStatus;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하여 PurchaseStatus를 문자열로 저장
  @Column(nullable = false, length = 20)
  PurchaseStatus status; // 구매 상태

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;


  @Builder
  public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
  }

  public void setStatus(PurchaseStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

}