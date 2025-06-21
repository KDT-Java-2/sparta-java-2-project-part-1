package com.sparta.ecommerce.common.enums;

public enum PurchaseStatus {
  PENDING("대기"), COMPLETED("완료"), CANCELLED("취소");

  private final String description;

  PurchaseStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
