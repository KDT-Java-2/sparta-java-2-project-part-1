package com.moveuk.ecommerce.domain.product;

import com.moveuk.ecommerce.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // 연관관계: Category (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder.Default
    @Column(name = "visible_yn", nullable = false, length = 1)
    private String visibleYn = "Y";

    @Builder.Default
    @Column(name = "deleted_yn", nullable = false, length = 1)
    private String deletedYn = "N";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDetail(String name, String description, BigDecimal bigDecimal, Category category) {
        this.name = name;
        this.description = description;
        this.price = bigDecimal;
        this.category = category;
    }

    public void removeProduct() {
        this.deletedYn = "Y";
        this.deletedAt = LocalDateTime.now();
    }
}
