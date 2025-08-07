package com.socialcommerce.domain.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.user.entity.User;
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
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@Table(name = "cart")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  List<CartItem> cartItems = new ArrayList<>();

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "product_id", nullable = false)
//  @JsonBackReference
//  Product product;
//
//  @Column(nullable = false)
//  int quantity;
//
//  @Column(nullable = false, updatable = false)
//  @CreationTimestamp
//  LocalDateTime createdAt;
//
//  @Column
//  @UpdateTimestamp
//  LocalDateTime updatedAt;

  @Builder
  public Cart(User user) {
    this.user = user;
  }

  public void addCartItem(CartItem item) {
    item.setCart(this);
    this.cartItems.add(item);
  }

  public void removeItem(CartItem item) {
    item.setCart(null);
    this.cartItems.remove(item);
  }
}
