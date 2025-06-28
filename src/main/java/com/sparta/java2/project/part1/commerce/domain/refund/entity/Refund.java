package com.sparta.java2.project.part1.commerce.domain.refund.entity;

import com.sparta.java2.project.part1.commerce.common.entity.BaseTimeEntity;
import com.sparta.java2.project.part1.commerce.common.enums.RefundStatus;
import com.sparta.java2.project.part1.commerce.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Refund extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false, updatable = false)
    Purchase purchase;

    @Column
    String reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RefundStatus status;
}
