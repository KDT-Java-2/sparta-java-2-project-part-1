package com.sparta.bootcamp.shop.domain.purchase.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.sparta.bootcamp.shop.common.enums.PurchaseStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseCancelResponse {

    private Long purchaseId;

    private PurchaseStatus status;

    private LocalDateTime cancelledAt;

    private List<PurchaseProductResponse> cancelledProducts;

    private String message;

}