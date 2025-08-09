package com.sparta.java_02.domain.purchase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.java_02.common.enums.PurchaseStatus;
import com.sparta.java_02.domain.refund.entity.Refund;
import com.sparta.java_02.domain.user.entity.User;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  PurchaseStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

  @OneToOne(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Refund refund;

  @Builder
  public Purchase(
      User user,
      BigDecimal totalPrice,
      PurchaseStatus status
  ) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
  }

  public void setStatus(PurchaseStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }
}
