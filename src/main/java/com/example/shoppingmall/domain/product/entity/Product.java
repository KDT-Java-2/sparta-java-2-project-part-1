package com.example.shoppingmall.domain.product.entity;

import com.example.shoppingmall.domain.cart.entity.CartItem;
import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.purchase.entity.PurchaseProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  String name;

  @Column(columnDefinition = "TEXT")
  String description;

  @Column(precision = 19, scale = 2)
  BigDecimal price;

  @Column
  Integer stock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  Category category;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<CartItem> cartItems = new ArrayList<>();

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<PurchaseProduct> purchaseProducts = new ArrayList<>();

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

  @Builder
  public Product(
      String name,
      String description,
      BigDecimal price,
      Integer stock,
      Category category
  ) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.category = category;
  }
} 