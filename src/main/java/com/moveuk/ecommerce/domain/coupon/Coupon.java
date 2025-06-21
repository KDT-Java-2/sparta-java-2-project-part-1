package com.moveuk.ecommerce.domain.coupon;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "discount_type", nullable = false)
    private String discountType; // FIXED or PERCENT

    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "minimum_order_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal minimumOrderAmount;

    @Column(name = "limit_count", nullable = false)
    private int limitCount;

    @Column(name = "issued_count", nullable = false)
    private int issuedCount;

    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;

    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    public boolean isValid() {
        var now = LocalDateTime.now();
        return now.isAfter(validFrom) && now.isBefore(validUntil);
    }

    public void incrementIssuedCount() {
        if (issuedCount >= limitCount) {
            throw new IllegalStateException("쿠폰 발급 한도 초과");
        }
        this.issuedCount++;
    }
}
