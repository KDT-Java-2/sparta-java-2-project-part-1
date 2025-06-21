package com.sparta.ecommerce.common.enums;

public enum RefundStatus {
  PENDING("대기"), APPROVED("승인"), REJECTED("거절");

  private final String description;

  RefundStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
