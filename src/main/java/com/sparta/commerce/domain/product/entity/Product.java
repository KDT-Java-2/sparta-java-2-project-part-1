package com.sparta.commerce.domain.product.entity;

import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.seller.entity.Seller;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.swing.text.Caret;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.ToOne;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seller_id", nullable = false)
  Seller seller;

  @Column(nullable = false)
  String name;

  @Column
  String brand;

  @Lob
  @Column(nullable = false)
  String description;

  @Column
  BigDecimal basePrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Product(Seller seller, String name, String brand, String description, BigDecimal basePrice,
      Category category) {
    this.seller = seller;
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.basePrice = basePrice;
    this.category = category;
  }

  public void update(String name, String description, String brand, BigDecimal basePrice){
    this.name = name;
    this.description = description;
    this.brand = brand;
    this.basePrice = basePrice;
  }
}
