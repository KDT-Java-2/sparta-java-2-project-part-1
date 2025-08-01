package com.socialcommerce.domain.purchase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.socialcommerce.common.enums.PurchaseStatus;
import com.socialcommerce.domain.coupon.entity.Coupon;
import com.socialcommerce.domain.refund.entity.Refund;
import com.socialcommerce.domain.user.entity.User;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
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
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  User user;

  @Column
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  PurchaseStatus status;

  @Column(nullable = false, columnDefinition = "TEXT")
  String shippingAddress;

  @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
  List<PurchaseProduct> purchaseProducts;

  // 전체환불
  @OneToOne(mappedBy = "purchase", fetch = FetchType.LAZY)
  Refund refund;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coupon_id")
  Coupon coupon;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status, String shippingAddress, Coupon coupon) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
    this.shippingAddress = shippingAddress;
    this.coupon = coupon;
  }

  public void setStatus(PurchaseStatus status) {
     this.status = status;
  }

  public void addPurchaseProduct(PurchaseProduct pp) {
    this.purchaseProducts.add(pp);
    pp.setPurchase(this);
  }
}
