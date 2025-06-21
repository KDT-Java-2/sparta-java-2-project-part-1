package com.sparta.commerce.domain.cart;

import com.sparta.commerce.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
import jakarta.persistence.Id;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Builder
  public CartItem(Cart cart, Product product, Integer quantity) {
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product; // 장바구니에 담긴 상품

  @Column(nullable = false)
  Integer quantity; // 장바구니에 담긴 상품의 수량

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  /**
   * 장바구니 상품의 수량을 변경하는 메서드
   * @param newQuantity 새로운 수량 (1 이상)
   */
  public void updateQuantity(Integer newQuantity) {
    if (newQuantity == null || newQuantity <= 0) {
      throw new IllegalArgumentException("상품 수량은 1개 이상이어야 합니다.");
    }
    this.quantity = newQuantity;
  }
}
