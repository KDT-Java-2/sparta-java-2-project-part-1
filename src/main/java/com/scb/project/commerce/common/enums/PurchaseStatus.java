package com.scb.project.commerce.common.enums;

public enum PurchaseStatus {
    PENDING,    // 대기
    PAID,   // 결제완료
    FAILED, // 결제실패
    PREPARING,  // 상품 준비중
    CANCELED,   // 주문 취소
    REFUNDED,   // 환불 완료
    SHIPPED,    // 배송중
    DELIVERED   // 배송 완료
}
