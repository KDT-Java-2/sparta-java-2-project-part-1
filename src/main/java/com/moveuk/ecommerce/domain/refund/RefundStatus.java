package com.moveuk.ecommerce.domain.refund;

public enum RefundStatus {
    PENDING, COMPLETED, CANCELED;

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public boolean isCancellable() {
        return this == PENDING;
    }
}
