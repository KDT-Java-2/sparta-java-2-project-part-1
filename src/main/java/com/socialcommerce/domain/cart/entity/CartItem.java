package com.socialcommerce.domain.cart.entity;

import com.socialcommerce.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "cart_item")
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(nullable = false)
  int quantity;

  @CreationTimestamp
  LocalDateTime createdAt;

  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public CartItem(
      Product product,
      int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public void addQuantity(int qty) {
    this.quantity += qty;
  }

  public void changeQuantity(int qty) {
    this.quantity = qty;
  }
}
