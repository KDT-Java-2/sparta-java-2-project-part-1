package org.example.prj_part_1.domain.product.entity;

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
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.prj_part_1.domain.category.entitiy.Category;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  String name;

  @Column
  String description;

  @Column
  BigDecimal price;

  @Column
  Integer stock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  Category category;

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
