package com.dogworld.dogdog.product.domain;

import com.dogworld.dogdog.product.interfaces.dto.request.ProductRequest;
import com.dogworld.dogdog.common.domain.BaseEntity;
import com.dogworld.dogdog.category.domain.Category;
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
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 200, nullable = false)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false, precision = 18, scale = 2)
  private BigDecimal price;

  @Column(nullable = false)
  private int stock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductStatus status;

  @Column(length = 500)
  private String thumbnailUrl;

  @Column(nullable = false)
  private boolean deleted;

  @Builder
  public Product(String name, String description, BigDecimal price, int stock, Category category,
      ProductStatus status, String thumbnailUrl, boolean deleted) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.category = category;
    this.status = (status == null) ? ProductStatus.SELLING : status;
    this.thumbnailUrl = thumbnailUrl;
    this.deleted = deleted;
  }

  public static Product create(ProductRequest request, Category category) {
    return Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(BigDecimal.valueOf(request.getPrice()))
        .stock(request.getStock())
        .category(category)
        .status(request.getStatus())
        .thumbnailUrl(request.getThumbnailUrl())
        .build();
  }

  public void update(ProductRequest request, Category category) {
    this.name = request.getName();
    this.description = request.getDescription();
    this.price = BigDecimal.valueOf(request.getPrice());
    this.stock = request.getStock();
    this.category = category;
    this.status = request.getStatus();
    this.thumbnailUrl = request.getThumbnailUrl();
  }
}
