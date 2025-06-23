package com.sparta.commerce.common.enums;

import lombok.Getter;

@Getter
public enum PurchaseStatus {
  ORDERED("주문 완료"),
  PREPARING("상품 준비중"),
  SHIPPED("배송중"),
  DELIVERED("배송 완료"),
  CANCELED("취소"),
  RETURNED("반품 완료"),
  REFUNDED("환불 완료");

  private final String description;

  PurchaseStatus(String description) {
    this.description = description;
  }
}
