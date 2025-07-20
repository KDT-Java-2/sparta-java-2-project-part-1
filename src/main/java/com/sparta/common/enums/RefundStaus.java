package com.sparta.common.enums;

public enum RefundStaus {
  PENDING("대기"), APPROVED("승인"), REJECTED("거절");

  private final String description;

  RefundStaus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
