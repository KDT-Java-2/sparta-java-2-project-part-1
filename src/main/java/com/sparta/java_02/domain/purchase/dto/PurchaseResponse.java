package com.sparta.java_02.domain.purchase.dto;

import com.sparta.java_02.common.enums.PurchaseStatus;
import com.sparta.java_02.domain.purchase.entity.Purchase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {

  private Long purchaseId;
  private Long userId;
  private BigDecimal totalPrice;
  private PurchaseStatus status;
  private LocalDateTime createdAt;

  public static PurchaseResponse fromEntity(Purchase purchase) {
    return new PurchaseResponse(
        purchase.getId(),
        purchase.getUser().getId(),
        purchase.getTotalPrice(),
        purchase.getStatus(),
        purchase.getCreatedAt()
    );
  }
}