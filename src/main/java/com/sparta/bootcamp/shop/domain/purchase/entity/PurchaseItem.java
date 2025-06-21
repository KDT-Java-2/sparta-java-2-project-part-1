package com.sparta.bootcamp.shop.domain.purchase.entity;

import com.sparta.bootcamp.shop.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "purchase_item")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static PurchaseItem create(Purchase purchase, Product product, int quantity, BigDecimal price) {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.purchase = purchase;
        purchaseItem.product = product;
        purchaseItem.quantity = quantity;
        purchaseItem.price = price;
        purchaseItem.createdAt = LocalDateTime.now();
        purchaseItem.updatedAt = LocalDateTime.now();
        return purchaseItem;
    }
}