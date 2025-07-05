package com.dogworld.dogdog.purchase.domain;

import com.dogworld.dogdog.common.domain.BaseEntity;
import com.dogworld.dogdog.member.domain.Member;
import com.dogworld.dogdog.purchaseproduct.domain.PurchaseProduct;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

  @Column(nullable = false, precision = 18, scale = 2)
  private BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PurchaseStatus status;

  @Column(nullable = false)
  private LocalDateTime orderedAt;

  private LocalDateTime canceledAt;

  @Builder
  public Purchase(Member member, List<PurchaseProduct> purchaseProducts, BigDecimal totalPrice,
      PurchaseStatus status, LocalDateTime orderedAt) {
    this.member = member;
    this.purchaseProducts = purchaseProducts;
    this.totalPrice = totalPrice;
    this.status = status == null ? PurchaseStatus.PENDING : status;
    this.orderedAt = orderedAt;
  }
}
