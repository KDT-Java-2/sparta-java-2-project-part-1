package com.example.shoppingmall.domain.cart.entity;

import com.example.shoppingmall.domain.user.entity.User;
import jakarta.persistence.CascadeType;
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
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  User user;

  @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  List<CartItem> cartItems = new ArrayList<>();

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Builder
  public Cart(User user) {
    this.user = user;
  }

  // 장바구니 아이템 추가
  public void addCartItem(CartItem cartItem) {
    this.cartItems.add(cartItem);
    cartItem.setCart(this);
  }

  // 장바구니 아이템 제거
  public void removeCartItem(CartItem cartItem) {
    this.cartItems.remove(cartItem);
    cartItem.setCart(null);
  }

  // 장바구니 비우기
  public void clearCart() {
    this.cartItems.clear();
  }

  // 총 아이템 수
  public int getTotalItemCount() {
    return this.cartItems.stream()
        .mapToInt(CartItem::getQuantity)
        .sum();
  }
} 