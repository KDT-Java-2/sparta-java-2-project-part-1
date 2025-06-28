package com.sparta.java2.project.part1.commerce.domain.product.entity;

import com.sparta.java2.project.part1.commerce.common.entity.BaseTimeEntity;
import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @Column(nullable = false)
    String name;

    @Setter
    @Column
    String description;

    @Setter
    @Column
    BigDecimal price;

    @Setter
    @Column
    Integer stock;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @Builder
    public Product(String name, String description, BigDecimal price, Integer stock, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
