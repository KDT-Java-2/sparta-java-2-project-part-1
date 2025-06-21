package com.tommy.ecommerproject.domain.product.entity;

import com.tommy.ecommerproject.domain.category.Category;
import com.tommy.ecommerproject.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, unique = true, length = 36)
  String alias;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  String description;

  @Column(nullable = false)
  BigDecimal price;

  @Column
  @ColumnDefault("0")   // 초기 생성 시 재고 0
  Integer stock;

  // 게시자
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User publisher;

  //카테고리 id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category;

  @Column
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Product(
      String alias,
      String name,
      String description,
      BigDecimal price,
      Integer stock,
      User publisher,
      Category category
  ) {
    this.alias = alias;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.publisher = publisher;
    this.category = category;
  }

  @PrePersist
  public void generatedUuid() {
    if(this.alias == null){ // alias가 존재하지 않으면 UUID를 생성하여 String으로 넣어준다.
      this.alias = UUID.randomUUID().toString();
    }
  }

}
