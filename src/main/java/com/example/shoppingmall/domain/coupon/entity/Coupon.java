package com.example.shoppingmall.domain.coupon.entity;

import com.example.shoppingmall.common.enums.DiscountType;
import com.example.shoppingmall.domain.user.entity.UserCoupon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "coupon")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY)
    List<UserCoupon> userCoupons = new ArrayList<>();

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false, length = 50, unique = true)
    String code;

    @Column(columnDefinition = "TEXT")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    DiscountType discountType;

    @Column(nullable = false)
    BigDecimal discountValue;

    @Column(nullable = false)
    BigDecimal minPurchaseAmount;

    @Column(nullable = false)
    LocalDateTime validFrom;

    @Column(name = "valid_until", nullable = false)
    LocalDateTime validUntil;

    @Column(nullable = false)
    Boolean isActive;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public Coupon(String name, String code, String description, DiscountType discountType, BigDecimal discountValue, BigDecimal minPurchaseAmount, LocalDateTime validFrom, LocalDateTime validUntil, Boolean isActive) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minPurchaseAmount = minPurchaseAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.isActive = isActive;
    }

    public void update(String name, String code, String description, DiscountType discountType, BigDecimal discountValue, BigDecimal minPurchaseAmount, LocalDateTime validFrom, LocalDateTime validUntil, Boolean isActive) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minPurchaseAmount = minPurchaseAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.isActive = isActive;
    }
} 