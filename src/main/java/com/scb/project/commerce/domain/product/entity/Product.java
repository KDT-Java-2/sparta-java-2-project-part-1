package com.scb.project.commerce.domain.product.entity;

import com.scb.project.commerce.common.enums.ProductStatus;
import com.scb.project.commerce.domain.admin.dto.AdminProductUpdateRequest;
import com.scb.project.commerce.domain.category.entity.Category;
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

    @Column(nullable = false)
    String productName; // 상품명

    @Column(nullable = false)
    String brandName;   // 브랜드명

    @Column
    String description; // 상품 설명

    @Column(nullable = false)
    BigDecimal price;   // 상품 가격

    @Column(nullable = false)
    int stock;  // 재고 수량

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    ProductStatus status;   // 판매 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;  // 카테고리 ID

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public Product(
        String productName,
        String brandName,
        String description,
        BigDecimal price,
        int stock,
        ProductStatus status,
        Category category
    ) {
        this.productName = productName;
        this.brandName = brandName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.category = category;
    }

    /**
     * 관리자 상품 수정 요청에 따라 상품 정보를 갱신합니다.
     *
     * <p>요청 필드 중 null이 아닌 값만 반영하여 선별적으로 수정하며,
     * 카테고리도 null이 아닌 경우에만 변경합니다.
     * <br>이를 통해 부분 수정(PATCH) 방식의 유연한 업데이트를 지원합니다.
     *
     * @param request  관리자 상품 수정 요청 DTO
     * @param category 요청된 카테고리 엔티티 (null인 경우 기존 카테고리 유지)
     */
    public void updateProductInfo(AdminProductUpdateRequest request, Category category) {

        if (request.getName() != null) {
            this.productName = request.getName();
        }

        if (request.getBrandName() != null) {
            this.brandName = request.getBrandName();
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

        if (category != null) {
            this.category = category;
        }
    }
}
