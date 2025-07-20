package com.js.commerce.domain.cart.entity;

import com.js.commerce.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  Product product;

  @Column(nullable = false)
  int quantity;

  @Column(nullable = false)
  BigDecimal priceAtAdd; // 담을 당시 가격

  @Column
  String itemOption; // 상품 옵션 정보

  public CartItem(
      Cart cart,
      Product product,
      int quantity,
      BigDecimal priceAtAdd,
      String itemOption
  ) {
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
    this.priceAtAdd = priceAtAdd;
    this.itemOption = itemOption;
  }

  /**
   * 구매 수량 변경
   **/
  public void changeQuantity(int newQuantity) {
    if (newQuantity > 0) {
      this.quantity = newQuantity;
    }
  }
}
