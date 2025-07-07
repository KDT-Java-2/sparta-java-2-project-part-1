package com.sparta.commerceProject.domain.purchase.entity;

import com.sparta.commerceProject.common.enums.PurchaseStatus;
import com.sparta.commerceProject.domain.purchaseItem.entity.PurchaseItem;
import com.sparta.commerceProject.domain.refund.entity.Refund;
import com.sparta.commerceProject.domain.user.entity.User;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
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
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  PurchaseStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @OneToMany(mappedBy = "purchase")
  List<PurchaseItem> purchaseItems = new ArrayList<>();

  @OneToMany(mappedBy = "purchase")
  List<Refund> refunds = new ArrayList<>();

  @Builder
  public Purchase(Long id, Long userId, BigDecimal totalPrice, PurchaseStatus status) {
    this.totalPrice = totalPrice;
    this.status = status;
  }
}
