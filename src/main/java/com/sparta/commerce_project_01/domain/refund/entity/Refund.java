package com.sparta.commerce_project_01.domain.refund.entity;

import com.sparta.commerce_project_01.common.enums.RepundStatus;
import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor // 빈생성자를 만들어 준다  vs  AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // 기본 접근 제한자 지정
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    Purchase purchase;

    @Column
    String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    RepundStatus status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Refund(Purchase purchase, String reason, RepundStatus status) {
        this.purchase = purchase;
        this.reason = reason;
        this.status = status;
    }
}
