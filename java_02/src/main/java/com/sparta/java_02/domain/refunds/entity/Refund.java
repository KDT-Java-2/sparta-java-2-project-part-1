package com.sparta.java_02.domain.refunds.entity;

import com.sparta.java_02.domain.purchase.entity.Purchase;
import com.sparta.java_02.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false, unique = true)
    Purchase purchase;

    @Column(length = 255)
    String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    RefundStatus refundStatus;

}
