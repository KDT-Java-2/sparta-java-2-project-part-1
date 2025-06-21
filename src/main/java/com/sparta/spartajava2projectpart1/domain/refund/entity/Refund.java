package com.sparta.spartajava2projectpart1.domain.refund.entity;

import com.sparta.spartajava2projectpart1.domain.purchase.entity.Purchase;
import com.sparta.spartajava2projectpart1.global.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Purchase purchase;

    @Column
    String reason;

    @Column
    @Enumerated(EnumType.STRING)
    RefundStatus refundStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Refund(Purchase purchase, String reason, RefundStatus refundStatus) {
        this.purchase = purchase;
        this.reason = reason;
        this.refundStatus = refundStatus;
    }
}
