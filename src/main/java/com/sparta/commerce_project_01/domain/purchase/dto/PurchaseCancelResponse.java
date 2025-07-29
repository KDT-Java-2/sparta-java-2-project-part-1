package com.sparta.commerce_project_01.domain.purchase.dto;

import com.sparta.commerce_project_01.common.enums.PurchaseStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseCancelResponse {

  final Long purchaseId;
  final PurchaseStatus purchaseStatus;
  final LocalDateTime cancelledAt;
  final String message;
  final List<PurchaseProductResponse> cancelledProducts;
}
