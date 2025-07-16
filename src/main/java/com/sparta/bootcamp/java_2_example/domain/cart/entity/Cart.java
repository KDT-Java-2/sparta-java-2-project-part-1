package com.sparta.bootcamp.java_2_example.domain.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.bootcamp.java_2_example.domain.product.entity.Product;
import com.sparta.bootcamp.java_2_example.domain.purchase.entity.Purchase;
import com.sparta.bootcamp.java_2_example.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(nullable = false)
  Integer quantity;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Cart(
      User user,
      Product product,
      Integer quantity
  ) {
    this.user = user;
    this.product = product;
    this.quantity = quantity;
  }

}
