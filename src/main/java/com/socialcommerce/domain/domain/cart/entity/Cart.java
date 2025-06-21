package com.socialcommerce.domain.domain.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialcommerce.domain.domain.product.entity.Product;
import com.socialcommerce.domain.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

@Getter
@Entity
@Table(name = "cart")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor  // 빈생성자를 만들어준다. 항상 있는데 귀찮기 때문에
@FieldDefaults(level = AccessLevel.PRIVATE) // 모든 접근 제한이 private 으로 바뀐다.
public class Cart {
  // 생성방식이 IDENTITY 면 sql 안에 id BIGINT AUTO_INCREMENT PRIMARY KEY 의 AUTO_INCREMENT 를 따라간다.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  @JsonBackReference
  Product product;

  @Column(nullable = false)
  int quantity;

  @Column(nullable = false, updatable = false)  // updatable = false 수정 불가능
  @CreationTimestamp  // CURRENT_TIMESTAMP 와 동일
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp  // UPDATE_TIMESTAMP 와 동일
  LocalDateTime updatedAt;

  @Builder
  public Cart(User user, Product product, int quantity) {
    this.user = user;
    this.product = product;
    this.quantity = quantity;
  }
}
