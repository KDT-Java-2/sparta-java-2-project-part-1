package com.sparta.java_02.domain.purchase.entity;

import com.sparta.java_02.enums.PurchaseStatus;
import com.sparta.java_02.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column
    BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    PurchaseStatus status;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
