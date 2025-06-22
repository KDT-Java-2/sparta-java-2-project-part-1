package com.dogworld.dogdog.domain.purchase;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseStatus {
  PENDING("결제 대기"),
  COMPLETED("결제 완료"),
  CANCELED("주문 취소")
  ;

  private final String text;
}
