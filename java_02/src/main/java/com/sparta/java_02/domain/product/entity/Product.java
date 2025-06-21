package com.sparta.java_02.domain.product.entity;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    Long id;

    @Column
    String name;

    @Column
    String description;

    @Column
    BigDecimal price;

    @Column
    Integer stock;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Product(String name, String description, BigDecimal price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}

