package com.sparta.commerceProject.domain.product.entity;

import com.sparta.commerceProject.domain.category.entity.Category;
import com.sparta.commerceProject.domain.purchaseItem.entity.PurchaseItem;
import com.sparta.commerceProject.domain.refund.entity.Refund;
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

  @Column(nullable = false)
  String name;

  @Column
  String description;

  @Column
  Integer stock;

  @Column
  String status;

  @Column
  String imageUrl;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  Category category;

  @OneToMany(mappedBy = "product")
  List<PurchaseItem> purchaseItems = new ArrayList<>();

  @OneToMany(mappedBy = "product")
  List<Refund> refunds = new ArrayList<>();

  @Builder
  public Product(String name, String description, Integer stock, String status) {
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.status = status;
  }
}
