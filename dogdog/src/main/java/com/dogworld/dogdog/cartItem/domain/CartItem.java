package com.dogworld.dogdog.cartItem.domain;

import com.dogworld.dogdog.common.domain.BaseEntity;
import com.dogworld.dogdog.cart.domain.Cart;
import com.dogworld.dogdog.product.domain.Product;
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
public class CartItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false, precision = 18, scale = 2)
  private BigDecimal price;

  @Column(nullable = false, precision = 18, scale = 2)
  private BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CartItemStatus status;

  @Builder
  public CartItem(Cart cart, Product product, int quantity) {
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;

    this.price = product.getPrice();
    this.totalPrice = calculateTotalPrice();
    this.status = CartItemStatus.ACTIVE;
  }

  private BigDecimal calculateTotalPrice() {
    return price.multiply(BigDecimal.valueOf(quantity));
  }
}
