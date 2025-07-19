package com.sparta.ecommerce.domain.product.entity;

import com.sparta.ecommerce.domain.category.entity.Category;
import com.sparta.ecommerce.domain.product.dto.ProductCreateRequest;
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
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  Category category;

  @Column(nullable = false)
  private String productNm;

  @Column
  String description;

  @Column(nullable = false)
  BigDecimal price;

  @Column
  private BigDecimal discountPrice;

  @Column(nullable = false)
  Integer stock;

  @Column
  String imageUrl;

  @Column
  Boolean isActive;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

  @Builder
  public Product(Category category, String productNm, String description, BigDecimal price,
      BigDecimal discountPrice, Integer stock, String imageUrl, Boolean isActive) {
    this.category = category;
    this.productNm = productNm;
    this.description = description;
    this.price = price;
    this.discountPrice = discountPrice;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.isActive = isActive;
  }

  public void update(ProductCreateRequest request, Category category) {
    this.productNm = request.getName();
    this.category = category;
    this.description = request.getDescription();
    this.price = request.getPrice();
    this.stock = request.getStock();
    if (request.getImageUrl() != null) {
      this.imageUrl = request.getImageUrl();
    }
    if (request.getIsActive() != null) {
      this.isActive = request.getIsActive();
    }
  }
}