package com.sparta.javamarket.domain.product.entity;


import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Builder.Default;
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
public class Product { // 상품

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "category_id")
//  Category category;

  Long categoryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column
  String name;

  @Column
  String description;



  @Column
  BigDecimal price;

  @Column
  Integer stock;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Product(User user, String name, String description, BigDecimal price, Integer stock) {
    this.user = user;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
  }
}