package com.dogworld.dogdog.refund.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefundStatus {
  REQUESTED("환불 요청"),
  APPROVED("환불 승인"),
  REJECTED("환불 거절"),
  COMPLETED("환불 완료"),
  CANCELED("환불 취소");

  private final String text;
}
