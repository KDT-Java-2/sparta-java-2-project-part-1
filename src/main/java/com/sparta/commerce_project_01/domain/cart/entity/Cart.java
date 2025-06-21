package com.sparta.commerce_project_01.domain.cart.entity;


import com.sparta.commerce_project_01.domain.product.entity.Product;
import com.sparta.commerce_project_01.domain.user.entity.User;
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

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor // 빈생성자를 만들어 준다  vs  AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // 기본 접근 제한자 지정
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    int price;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Cart(User user, Product product, int quantity, int price) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

}
