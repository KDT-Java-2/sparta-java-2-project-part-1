package com.sparta.coupang_commerce.domain.purchase.dto;

import com.sparta.coupang_commerce.common.enums.PurchaseStatusType;
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

  Long purchaseId;

  PurchaseStatusType purchaseStatus;

  LocalDateTime canceledAt;

  String message;

  List<PurchaseProductResponse> cancelledProducts;

}
