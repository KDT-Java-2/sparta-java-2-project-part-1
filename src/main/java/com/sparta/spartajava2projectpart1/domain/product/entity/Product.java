package com.sparta.spartajava2projectpart1.domain.product.entity;

import com.sparta.spartajava2projectpart1.domain.cart.entity.Cart;
import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import com.sparta.spartajava2projectpart1.domain.product.dto.CategoryUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column
    String description;

    @Column
    BigDecimal price;

    @Column(nullable = false)
    Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "product")
    List<Cart> carts = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Product(String name, String description, BigDecimal price, Integer stock, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public void updateProduct(CategoryUpdateRequest request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }

        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }

        if (request.getPrice() != null) {
            this.price = request.getPrice();
        }

        if (request.getStock() != null) {
            this.stock = request.getStock();
        }
    }

    public void changeCategory(Category category) {
        this.category = category;
    }
}