package com.sparta.commerce_project_01.domain.product.entity;
// Product.java

import com.sparta.commerce_project_01.domain.category.entity.Category;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String name;

  @Column(columnDefinition = "TEXT")
  String description;

  @Column(nullable = false)
  BigDecimal price;

  @Column(nullable = false)
  Integer stock;

  @Column
  String image;

  @Column
  boolean onSale = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category;


  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  private LocalDateTime updatedAt;

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

  public void reduceStock(Integer quantity) {
    this.stock -= quantity;
  }

  public void increaseStock(int quantity) {
    this.stock += quantity;
  }
}
