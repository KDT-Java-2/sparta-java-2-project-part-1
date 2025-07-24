package com.sparta.coupang_commerce.domain.purchase.dto;


import com.sparta.coupang_commerce.common.enums.PurchaseStatusType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProductRequest {

  Long productId;

  Integer quantity;

  PurchaseStatusType status;
}
