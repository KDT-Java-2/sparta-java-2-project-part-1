package com.sparta.commerce.common.enums;

public enum PurchaseStatus {
  REGISTERED,          // 주문 등록됨
  PAYMENT_PENDING,     // 결제 대기 중
  PAYMENT_COMPLETED,   // 결제 완료
  PREPARING_DELIVERY,  // 배송 준비 중
  SHIPPING,            // 배송 중
  DELIVERED,           // 배송 완료
  CANCELLED            // 주문 취소됨
}
