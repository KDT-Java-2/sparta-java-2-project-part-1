package com.sparta.shopping.domain.product.entity;

import com.sparta.shopping.domain.category.entity.Category;
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
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  BigDecimal price;

  @Column
  String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  Category category;

  @Column(nullable = false)
  Integer stock;

  @Column(name="del_yn",nullable = false, length = 1)
  String delYn;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Column
  LocalDateTime deletedAt;

  @Builder
  public Product(String name, BigDecimal price, String description, Category category,
      Integer stock) {
    this.name = name;
    this.price = price;
    this.description = description;
    this.category = category;
    this.stock = stock;
    this.delYn = "N";
  }
}
