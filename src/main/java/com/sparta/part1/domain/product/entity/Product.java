package com.sparta.part1.domain.product.entity;

import com.sparta.part1.domain.cart.entity.Cart;
import com.sparta.part1.domain.category.entity.Category;
import com.sparta.part1.domain.purchase_product.entity.PurchaseProduct;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
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

  @Column(nullable = false)
  String name;

  String description;
  BigDecimal price;
  Integer stock;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<PurchaseProduct> purchaseProducts;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<Cart> carts;

  public void setName(String name) {
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }

  public void setDescription(String description) {
    if (StringUtils.hasText(description)) {
      this.description = description;
    }
  }
}
