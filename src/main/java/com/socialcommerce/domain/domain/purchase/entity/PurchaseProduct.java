package com.socialcommerce.domain.domain.purchase.entity;

import com.socialcommerce.domain.domain.product.entity.Product;
import com.socialcommerce.domain.domain.refund.entity.Refund;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "purchase_product")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchase_id", nullable = false)
  Purchase purchase;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(nullable = false)
  int quantity;

  @Column(nullable = false)
  BigDecimal price;

  @OneToOne(mappedBy = "purchaseProduct", fetch = FetchType.LAZY)
  Refund refund;

  @Builder
  public PurchaseProduct(Purchase purchase, Product product, int quantity, BigDecimal price) {
      this.purchase = purchase;
      this.product = product;
      this.quantity = quantity;
      this.price = price;
  }
}
