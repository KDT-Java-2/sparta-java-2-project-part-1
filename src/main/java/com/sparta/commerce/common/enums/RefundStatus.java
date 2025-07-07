package com.sparta.commerce.common.enums;

import lombok.Getter;

@Getter
public enum RefundStatus {
  REQUEST("요청"),
  APPROVED("승인"),
  PENDING("진행"),
  REJECTED("거절"),
  COMPLETED("완료");

  private final String description;

  RefundStatus(String description) {
    this.description = description;
  }
}
