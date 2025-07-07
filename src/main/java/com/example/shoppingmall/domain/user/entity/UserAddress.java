package com.example.shoppingmall.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "user_address")
@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

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

    @Column(nullable = false)
    Boolean isDefault = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public UserAddress(User user, String recipientName, String address, String zipCode, String phoneNumber, String memo, Boolean isDefault) {
        this.user = user;
        this.recipientName = recipientName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.isDefault = isDefault != null ? isDefault : false;
    }

    public void update(String recipientName, String address, String zipCode, String phoneNumber, String memo, Boolean isDefault) {
        this.recipientName = recipientName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.isDefault = isDefault != null ? isDefault : false;
    }
} 