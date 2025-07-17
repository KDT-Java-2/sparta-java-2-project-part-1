package com.scb.masterCourse.commerce.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scb.masterCourse.commerce.domain.brand.entity.Brand;
import com.scb.masterCourse.commerce.domain.category.entity.Category;
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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonBackReference
    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Brand brand;

    @JsonBackReference
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Category category;

    @Column(nullable = false)
    String name;

    @Column
    String description;

    @Column(nullable = false)
    BigDecimal price;

    @Column
    Integer stock;

    @Column
    String thumbnailUrl;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updatedAt;

    @Builder
    public Product(
        Brand brand,
        Category category,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String thumbnailUrl
    ) {
        this.brand = brand;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.thumbnailUrl = thumbnailUrl;
    }
}
