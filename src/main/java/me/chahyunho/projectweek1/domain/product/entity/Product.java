package me.chahyunho.projectweek1.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import me.chahyunho.projectweek1.domain.category.entity.Category;
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
  private Long id;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(nullable = false)
  @Setter
  private String name;

  @Column(columnDefinition = "TEXT")
  @Setter
  private String description;

  @Column(nullable = false)
  @Setter
  private BigDecimal price;

  @Column(nullable = false)
  @Setter
  private Integer stock;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp // 엔티티 수정시 시간이 자동으로 기록됨
  @Column(nullable = false, updatable = false)
  LocalDateTime updatedAt;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public void decreaseStock(Integer quantity) {
    this.stock -= quantity;
  }


  @Builder
  public Product(String name, String description, BigDecimal price, Integer stock,
      Category category) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.category = category;
  }
}
