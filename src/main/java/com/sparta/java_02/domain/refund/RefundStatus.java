package com.sparta.java_02.domain.refund;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 환불 상태를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum RefundStatus {
    
    REQUESTED("환불요청", "환불이 요청된 상태"),
    UNDER_REVIEW("검토중", "환불 요청을 검토하는 상태"),
    APPROVED("승인됨", "환불이 승인된 상태"),
    REJECTED("거부됨", "환불이 거부된 상태"),
    PROCESSING("처리중", "환불을 처리하는 상태"),
    COMPLETED("완료됨", "환불이 완료된 상태"),
    CANCELED("취소됨", "환불 요청이 취소된 상태");

    private final String displayName;
    private final String description;

    /**
     * 상태 변경이 가능한지 확인
     */
    public boolean canTransitionTo(RefundStatus nextStatus) {
        return switch (this) {
            case REQUESTED -> nextStatus == UNDER_REVIEW || nextStatus == CANCELED;
            case UNDER_REVIEW -> nextStatus == APPROVED || nextStatus == REJECTED;
            case APPROVED -> nextStatus == PROCESSING || nextStatus == CANCELED;
            case PROCESSING -> nextStatus == COMPLETED;
            case REJECTED, COMPLETED, CANCELED -> false; // 최종 상태
        };
    }

    /**
     * 완료 상태인지 확인
     */
    public boolean isCompleted() {
        return this == COMPLETED;
    }

    /**
     * 최종 상태인지 확인 (더 이상 변경 불가)
     */
    public boolean isFinalStatus() {
        return this == COMPLETED || this == REJECTED || this == CANCELED;
    }
} 