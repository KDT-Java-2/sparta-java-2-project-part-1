package com.sparta.commerce.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(name = "original_name", nullable = false, length = 500)
  String originalName;

  @Column(name = "saved_name", nullable = false, length = 500)
  String savedName;

  @Column(name = "image_url", nullable = false, length = 500)
  String imageUrl;

  @Column(name = "is_thumbnail")
  boolean isThumbnail;

  @Builder
  public ProductImage(Product product, String originalName, String savedName, String imageUrl,
      boolean isThumbnail) {
    this.product = product;
    this.originalName = originalName;
    this.savedName = savedName;
    this.imageUrl = imageUrl;
    this.isThumbnail = isThumbnail;
  }
}
