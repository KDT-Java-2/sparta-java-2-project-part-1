package com.java.project_01.domain.purchase.entity;

import com.java.project_01.common.enums.PurchaseStatus;
import com.java.project_01.domain.user.entity.User;
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
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase { //주문

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  PurchaseStatus status;

  @Column(nullable = false)
  String orderNumber;

  @Column(nullable = false)
  String receiverName;

  @Column(nullable = false)
  String receiverPhone;

  @Column(nullable = false)
  String receiverAddress;

  @Column
  String receiverAddressDetail;

  @Column
  String receiverZipcode;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "purchase")
  List<PurchaseProduct> purchaseProductList = new ArrayList<>();

  @Builder
  public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status, String orderNumber,
      String receiverName, String receiverPhone, String receiverAddress,
      String receiverAddressDetail, String receiverZipcode) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
    this.orderNumber = orderNumber;
    this.receiverName = receiverName;
    this.receiverPhone = receiverPhone;
    this.receiverAddress = receiverAddress;
    this.receiverAddressDetail = receiverAddressDetail;
    this.receiverZipcode = receiverZipcode;
  }

}
