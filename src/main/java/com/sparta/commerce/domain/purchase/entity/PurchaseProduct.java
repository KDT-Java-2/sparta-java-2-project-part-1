package com.sparta.commerce.domain.purchase.entity;

import com.sparta.commerce.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
/**
 * 주문 - 상품 매핑 엔티티
 */
public class PurchaseProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Builder
  public PurchaseProduct(Purchase purchase, Product product, Integer quantity, BigDecimal totalPrice) {
    this.purchase = purchase;
    this.product = product;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase; // 주문 정보

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;  // 구매한 상품

  @Column(nullable = false)
  private Integer quantity; // 주문 수량

  @Column(nullable = false)
  private BigDecimal totalPrice; // 주문 시점의 가격

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

}
