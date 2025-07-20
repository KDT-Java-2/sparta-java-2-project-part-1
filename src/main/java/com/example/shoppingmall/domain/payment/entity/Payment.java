package com.example.shoppingmall.domain.payment.entity;

import com.example.shoppingmall.domain.purchase.entity.Purchase;
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

@Table(name = "payment")
@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    Purchase purchase;

    @Column(nullable = false)
    Integer amount;

    @Column(nullable = false, length = 20)
    String method;

    @Column(nullable = false, length = 20)
    String status;

    @Column(length = 100)
    String transactionId;

    @Column
    LocalDateTime paidAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public Payment(Purchase purchase, Integer amount, String method, String status, String transactionId, LocalDateTime paidAt) {
        this.purchase = purchase;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.transactionId = transactionId;
        this.paidAt = paidAt;
    }

    public void update(String status, String transactionId, LocalDateTime paidAt) {
        this.status = status;
        this.transactionId = transactionId;
        this.paidAt = paidAt;
    }
} 