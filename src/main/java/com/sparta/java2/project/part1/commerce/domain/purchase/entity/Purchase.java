package com.sparta.java2.project.part1.commerce.domain.purchase.entity;

import com.sparta.java2.project.part1.commerce.common.entity.BaseTimeEntity;
import com.sparta.java2.project.part1.commerce.common.enums.PurchaseStatus;
import com.sparta.java2.project.part1.commerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false, updatable = false)
    BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    PurchaseStatus status;

    @Builder
    public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
