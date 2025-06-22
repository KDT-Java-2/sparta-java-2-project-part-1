package com.moveuk.ecommerce.domain.purchase;

public enum PurchaseStatus {
    PENDING, COMPLETED, CANCELED;

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public boolean isCancellable() {
        return this == PENDING;
    }

}
