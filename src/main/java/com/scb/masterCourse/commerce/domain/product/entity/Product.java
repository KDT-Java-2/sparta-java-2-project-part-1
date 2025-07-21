package com.scb.masterCourse.commerce.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scb.masterCourse.commerce.common.enums.ProductStatus;
import com.scb.masterCourse.commerce.domain.brand.entity.Brand;
import com.scb.masterCourse.commerce.domain.category.entity.Category;
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
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.flywaydb.core.internal.util.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.ObjectUtils;

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

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    ProductStatus status;

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
        String thumbnailUrl,
        ProductStatus status
    ) {
        this.brand = brand;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
    }

    public void update(
        String name,
        Brand brand,
        Category category,
        String description,
        Integer price,
        Integer stock
    ) {
        if (StringUtils.hasText(name)) {
            this.name = name;
        }

        if (!ObjectUtils.isEmpty(brand)) {
            this.brand = brand;
        }

        if (!ObjectUtils.isEmpty(category)) {
            this.category = category;
        }

        if (!this.description.equals(description) && StringUtils.hasText(description)) {
            this.description = description;
        }

        if (price >= 0) {
            this.price = BigDecimal.valueOf(price);
        }

        if (stock >= 0) {
            this.stock = stock;
        }
    }
}
