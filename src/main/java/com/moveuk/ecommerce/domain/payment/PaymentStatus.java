package com.moveuk.ecommerce.domain.payment;

public enum PaymentStatus {
    PENDING,    // 결제 요청됨 (아직 처리 안됨)
    SUCCESS,    // 결제 성공
    FAILED,     // 결제 실패
    CANCELED;   // 결제 취소 (사용자 또는 시스템)

    public boolean canCancel() {
        return this == PENDING;
    }

    public boolean canRetry() {
        return this == FAILED;
    }
}
