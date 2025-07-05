package com.dogworld.dogdog.refund.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefundType {
  CHANGE_OF_MIND("단순 변심"),
  DEFECTIVE("상품 불량"),
  WRONG_DELIVERY("오배송"),
  OTHER("기타");

  private final String text;
}
