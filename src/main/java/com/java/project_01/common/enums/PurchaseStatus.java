package com.java.project_01.common.enums;

public enum PurchaseStatus {

  PENDING, // 결제 X
  PAID, // 결제 완료
  PREPARING, // 상품 준비 중
  SHIPPED, // 상품 발송
  DELIVERED, // 상품 배송 완료
  CANCELLED, // 구매 취소
  COMPLETED, // 구매 확정
  FAILED // 실패
}
