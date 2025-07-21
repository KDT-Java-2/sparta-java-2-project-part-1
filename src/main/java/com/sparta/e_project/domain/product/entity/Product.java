package com.sparta.e_project.domain.product.entity;

import com.sparta.e_project.domain.category.entity.Category;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category;

  @Column(nullable = false)
  String name;

  @Column
  String description;

  @Column(nullable = false)
  BigDecimal price;

  @Column(nullable = false)
  Integer stock;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

  public void putProduct(String name, String description, BigDecimal price, Integer stock,
      Category category) {
    if (!ObjectUtils.isEmpty(name)) {
      this.name = name;
    }
    if (!StringUtils.isEmpty(description)) {
      this.description = description;
    }
    if (!ObjectUtils.isEmpty(price)) {
      this.price = price;
    }
    if (!ObjectUtils.isEmpty(stock)) {
      this.stock = stock;
    }
    if (!ObjectUtils.isEmpty(category)) {
      this.category = category;
    }
  }

  @Builder
  public Product(Integer stock, BigDecimal price, String description, String name,
      Category category) {
    this.stock = stock;
    this.price = price;
    this.description = description;
    this.name = name;
    this.category = category;
  }
}
