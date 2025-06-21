package com.socialcommerce.domain.domain.coupon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

@Getter
@Entity
@Table(name = "coupon")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor  // 빈생성자를 만들어준다. 항상 있는데 귀찮기 때문에
@FieldDefaults(level = AccessLevel.PRIVATE) // 모든 접근 제한이 private 으로 바뀐다.
public class Coupon {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false, unique = true, length = 50)
    String code;
    @Column(nullable = false, length = 100)
    String name;

    // 원화가 아닌 다른 화폐를 사용한다면 소수점도 포함되야함.
    @Column(name = "discount_amount", precision = 10, scale = 2)
    BigDecimal discountAmount;

    @Column(name = "discount_percent")
    Integer discountPercent;

    @Column(name = "valid_from")
    LocalDateTime validFrom;

    @Column(name = "valid_to")
    LocalDateTime validTo;

    @Column(name = "min_purchase", precision = 10, scale = 2)
    BigDecimal minPurchase;

    @Column(name = "max_discount", precision = 10, scale = 2)
    BigDecimal maxDiscount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public Coupon(
            String code,
            String name,
            BigDecimal discountAmount,
            Integer discountPercent,
            LocalDateTime validFrom,
            LocalDateTime validTo,
            BigDecimal minPurchase,
            BigDecimal maxDiscount
    ) {
        this.code = code;
        this.name = name;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.minPurchase = minPurchase;
        this.maxDiscount = maxDiscount;
    }
}
