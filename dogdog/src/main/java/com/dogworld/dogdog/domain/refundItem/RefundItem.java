package com.dogworld.dogdog.domain.refundItem;

import com.dogworld.dogdog.domain.BaseEntity;
import com.dogworld.dogdog.domain.product.Product;
import com.dogworld.dogdog.domain.purchaseproduct.PurchaseProduct;
import com.dogworld.dogdog.domain.refund.Refund;
import com.dogworld.dogdog.domain.refund.RefundType;
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
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefundItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "refund_id", nullable = false)
  private Refund refund;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purcahse_product_id", nullable = false)
  private PurchaseProduct purchaseProduct;

  @Column(nullable = false)
  private int refund_quantity;

  @Column(nullable = false)
  private BigDecimal refund_amount;

  @Enumerated(EnumType.STRING)
  private RefundType  refundType;

  private String reason;
}

