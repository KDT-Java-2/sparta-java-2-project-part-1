package com.home.sparta_01_commerce.domain.purchase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.home.sparta_01_commerce.domain.product.entity.Product;
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
public class PurchaseProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(nullable = false)
  Integer quantity;

  @Column(nullable = false)
  BigDecimal price;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Builder
  public PurchaseProduct(Purchase purchase, Product product, Integer quantity, BigDecimal price) {
    this.purchase = purchase;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }
}

/*
CREATE TABLE purchase_product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  purchase_id BIGINT NOT NULL,   -- FK: 어떤 주문에 속하는지
  product_id BIGINT NOT NULL,    -- FK: 어떤 상품인지
  quantity INT NOT NULL,         -- 주문 수량
  price DECIMAL(10, 2) NOT NULL,  -- 주문 시점의 상품 가격
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
 */