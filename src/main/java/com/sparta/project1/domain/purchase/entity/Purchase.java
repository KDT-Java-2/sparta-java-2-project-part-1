package com.sparta.project1.domain.purchase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.project1.common.enums.PurchaseStatus;
import com.sparta.project1.domain.refund.entity.Refund;
import com.sparta.project1.domain.user.entity.User;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, name = "total_price")
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  PurchaseStatus status;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false, name = "updated_at")
  LocalDateTime updatedAt;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false) //Purchase 테이블에 User 테이블 id(pk) 참조하는 user_id 명칭의 왜래키 not null로 설정
  User user;

  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  List<Refund> refunds = new ArrayList<>();

  @Builder
  public Purchase(Long id, BigDecimal totalPrice, PurchaseStatus status) {
    this.id = id;
    this.totalPrice = totalPrice;
    this.status = status;
  }
}
