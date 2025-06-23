package com.tommy.ecommerproject.domain.cart.entity;

import com.tommy.ecommerproject.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartProduct {

  /*
  * Cart와 Product의
  * M:N 관계로 인한 중간테이블
  * */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  Cart cart;

  @ManyToOne
  @JoinColumn(name = "product_id")
  Product product;

  @Column
  @CreationTimestamp
  LocalDateTime createdAt;

  @Builder
  public CartProduct(Cart cart, Product product) {
    this.cart = cart;
    this.product = product;
  }
}
