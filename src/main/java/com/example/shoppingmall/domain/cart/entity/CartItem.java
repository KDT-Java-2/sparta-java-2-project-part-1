package com.example.shoppingmall.domain.cart.entity;

import com.example.shoppingmall.domain.product.entity.ProductVariant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "cart_item", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cart_id", "product_variant_id"})
})
@Entity
@Getter
@Setter
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
    @JoinColumn(name = "product_variant_id", nullable = false)
    ProductVariant productVariant;

    @Column(nullable = false)
    Integer quantity;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    Boolean isChecked = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public CartItem(Cart cart, ProductVariant productVariant, Integer quantity, BigDecimal price) {
        this.cart = cart;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.price = price;
    }

    // 수량 증가
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    // 수량 감소
    public void decreaseQuantity(int amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }

    // 수량 설정
    public void setQuantity(int quantity) {
        this.quantity = Math.max(1, quantity);
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void toggleCheck(boolean isChecked) {
        this.isChecked = isChecked;
    }
} 