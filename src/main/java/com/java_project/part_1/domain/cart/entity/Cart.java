package com.java_project.part_1.domain.cart.entity;

import com.java_project.part_1.domain.product.entity.Product;
import com.java_project.part_1.domain.user.entity.User;
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

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

  /**
   * cart 는 한 개의 상품(product)
   * <p>
   * 하나의 상품(product) 는 여러 사용자의 장바구니(cart) 에 담길 수 있
   * <p>
   * 하나의 사용자가 여러 상품을 장바구니에 담을 수 있음. 하나의 상품은 여러 사람의 장바구니에 존재 가능.
   * <p>
   * -> Cart 는 중간 역할
   */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(nullable = false)
  Integer quantity;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime addedAt;

  @Builder
  public Cart(
      User user,
      Product product,
      Integer quantity) {
    this.user = user;
    this.product = product;
    this.quantity = quantity;
  }
}
