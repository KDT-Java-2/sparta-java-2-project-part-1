package com.scb.project.commerce.common.enums;

public enum RefundStatus {
    PENDING,    // 환불 요청 대기
    APPROVED,   // 환불 승인
    REJECTED,   // 환불 거절
    PROCESSING, // 환불 처리중
    COMPLETED,  // 환불 완료
    FAILED  // 환불 실패
}
