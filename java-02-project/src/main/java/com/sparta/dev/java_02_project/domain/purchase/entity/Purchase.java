package com.sparta.dev.java_02_project.domain.purchase.entity;

import com.sparta.dev.java_02_project.common.enums.PurchaseStatus;
import com.sparta.dev.java_02_project.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table
@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column
    String orderNo;

    @Column
    BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    PurchaseStatus status; // 테이블에서는 varchar(20)으로 들어감


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    LocalDateTime updatedAt;

    public Purchase(
            User user,
            String orderNo,
            BigDecimal totalPrice,
            PurchaseStatus status
    ) {
        this.user = user;
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    @Builder

    public void setStatus(PurchaseStatus status) {
        if(ObjectUtils.isNotEmpty(status)) {
            this.status = status;
        }
    }
}
