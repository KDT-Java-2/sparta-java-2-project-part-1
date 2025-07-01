package com.sparta.java2.project.part1.commerce.common.enums;

public enum PurchaseStatus {
    // Purchase flow
    WAITING_FOR_PAYMENT,    // 입금 대기(무통장 입금 등)
    PAYMENT_CONFIRMED,      // 결제 완료
    PROCESSING,             // 상품 준비중
    SHIPPING,               // 배송중
    SHIPPED,                // 배송 완료
    COMPLETED,              // 구매 완료 (확정)

    // Cancels
    USER_CANCELLED,         // 결제 전/상품 준비 중 취소
    REFUNDING,              // 환불상태에서 관리

    // Exceptions
    DELIVERY_FAILED,        // 배송 실패
    SYSTEM_ERROR            // 시스템 오류
}