package com.scb.masterCourse.commerce.domain.refund.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scb.masterCourse.commerce.common.enums.RefundStatus;
import com.scb.masterCourse.commerce.domain.purchase.entity.Purchase;
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
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonBackReference
    @JoinColumn(name = "purchase_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Purchase purchase;

    @Column(nullable = false)
    String reason;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    RefundStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updatedAt;

    @Builder
    public Refund(Purchase purchase, String reason, RefundStatus status) {
        this.purchase = purchase;
        this.reason = reason;
        this.status = status;
    }
}
