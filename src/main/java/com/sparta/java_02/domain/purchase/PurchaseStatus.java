package com.sparta.java_02.domain.purchase;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 구매 상태를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum PurchaseStatus {
    
    PENDING("대기중", "결제 대기 상태"),
    PAYMENT_CONFIRMED("결제완료", "결제가 확인된 상태"),
    PREPARING("준비중", "상품을 준비하는 상태"),
    SHIPPED("배송중", "상품이 배송 중인 상태"),
    DELIVERED("배송완료", "상품이 배송 완료된 상태"),
    COMPLETED("구매완료", "구매가 최종 완료된 상태"),
    CANCELED("취소됨", "구매가 취소된 상태"),
    REFUND_REQUESTED("환불요청", "환불이 요청된 상태"),
    REFUNDED("환불완료", "환불이 완료된 상태");

    private final String displayName;
    private final String description;

    /**
     * 상태 변경이 가능한지 확인
     */
    public boolean canTransitionTo(PurchaseStatus nextStatus) {
        return switch (this) {
            case PENDING -> nextStatus == PAYMENT_CONFIRMED || nextStatus == CANCELED;
            case PAYMENT_CONFIRMED -> nextStatus == PREPARING || nextStatus == CANCELED;
            case PREPARING -> nextStatus == SHIPPED || nextStatus == CANCELED;
            case SHIPPED -> nextStatus == DELIVERED || nextStatus == CANCELED;
            case DELIVERED -> nextStatus == COMPLETED || nextStatus == REFUND_REQUESTED;
            case COMPLETED -> nextStatus == REFUND_REQUESTED;
            case REFUND_REQUESTED -> nextStatus == REFUNDED || nextStatus == COMPLETED;
            case CANCELED, REFUNDED -> false; // 최종 상태
        };
    }

    /**
     * 환불 가능한 상태인지 확인
     */
    public boolean isRefundable() {
        return this == DELIVERED || this == COMPLETED;
    }

    /**
     * 취소 가능한 상태인지 확인
     */
    public boolean isCancelable() {
        return this == PENDING || this == PAYMENT_CONFIRMED || 
               this == PREPARING || this == SHIPPED;
    }
} 