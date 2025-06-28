package com.example.shoppingmall.domain.purchase.entity;

import com.example.shoppingmall.common.enums.ShippingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

@Table(name = "purchase_shipping")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    Purchase purchase;

    @Column(nullable = false, length = 50)
    String recipientName;

    @Column(nullable = false, length = 255)
    String address;

    @Column(nullable = false, length = 20)
    String zipCode;

    @Column(nullable = false, length = 20)
    String phoneNumber;

    @Column(length = 500)
    String memo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    ShippingStatus status = ShippingStatus.PREPARING;

    @Column(length = 100)
    String trackingNumber;

    @Column
    LocalDateTime shippedAt;

    @Column
    LocalDateTime deliveredAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public PurchaseShipping(Purchase purchase, String recipientName, String address, String zipCode, String phoneNumber, String memo, ShippingStatus status, String trackingNumber, LocalDateTime shippedAt, LocalDateTime deliveredAt) {
        this.purchase = purchase;
        this.recipientName = recipientName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.shippedAt = shippedAt;
        this.deliveredAt = deliveredAt;
    }

    public void addTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void updateStatus(ShippingStatus status) {
        this.status = status;
    }
} 