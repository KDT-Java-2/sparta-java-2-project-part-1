package com.dogworld.dogdog.domain.purchaseproduct;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseProductStatus {
  ORDERED("주문 완료"),
  CANCELED("주문 취소"),
  RETURNED("반품")
  ;

  private final String text;
}
