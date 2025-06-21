package com.sparta.commerce.domain.product.entity;

import com.sparta.commerce.domain.category.entity.Category;
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

  @Builder
  public Product(
      String name,
      Category category,
      String description,
      BigDecimal price,
      Integer stock,
      String imageUrl) {
    this.name = name;
    this.category = category;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.imageUrl = imageUrl;
  }

  @Column
  String name; // 상품 이름

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category; // 상품 카테고리

  @Column
  String description; // 상품 설명

  @Column
  BigDecimal price; // 상품 가격

  @Column
  Integer stock; // 상품 재고 수량

  @Column
  String imageUrl; // 상품 이미지 URL

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  /**
   * 상품 정보 (이름, 설명, 이미지 URL)를 업데이트하는 메서드
   * @param newName 새로운 상품 이름
   * @param newDescription 새로운 상품 설명
   * @param newImageUrl 새로운 상품 이미지 URL
   */
  public void updateProductInfo(String newName, String newDescription, String newImageUrl) {
    if (newName != null && !newName.trim().isEmpty()) {
      this.name = newName;
    }
    if (newDescription != null && !newDescription.trim().isEmpty()) {
      this.description = newDescription;
    }
    if (newImageUrl != null && !newImageUrl.trim().isEmpty()) {
      this.imageUrl = newImageUrl;
    }
  }

  /**
   * 상품 가격을 업데이트하는 메서드
   * @param newPrice 새로운 가격
   */
  public void updatePrice(BigDecimal newPrice) {
    if (newPrice != null && newPrice.compareTo(BigDecimal.ZERO) >= 0) {
      this.price = newPrice;
    }
  }

  /**
   * 상품 재고를 증가시키는 메서드 (입고 등)
   * @param quantityToIncrease 증가시킬 수량
   */
  public void increaseStock(Integer quantityToIncrease) {
    if (quantityToIncrease != null && quantityToIncrease > 0) {
      this.stock += quantityToIncrease;
    } else {
      throw new IllegalArgumentException("증가시킬 수량은 0보다 커야 합니다.");
    }
  }

  /**
   * 상품 재고를 감소시키는 메서드 (판매 등)
   * @param quantityToDecrease 감소시킬 수량
   */
  public void decreaseStock(Integer quantityToDecrease) {
    if (quantityToDecrease != null && quantityToDecrease > 0) {
      if (this.stock - quantityToDecrease < 0) {
        throw new IllegalArgumentException("재고가 부족합니다.");
      }
      this.stock -= quantityToDecrease;
    } else {
      throw new IllegalArgumentException("감소시킬 수량은 0보다 커야 합니다.");
    }
  }
}
