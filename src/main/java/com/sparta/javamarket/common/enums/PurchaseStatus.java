package com.sparta.javamarket.common.enums;

public enum PurchaseStatus {
  PENDING,     // 결제가 아직 완료되지 않은 상태 (장바구니에서 주문 생성만 된 상태)
  COMPLETED,   // 결제가 완료되어 구매가 확정된 상태
  CANCELED,    // 사용자가 주문을 취소한 상태
  REFUNDED     // 환불이 완료된 상태 (Refund 엔티티와 연결됨)

}
