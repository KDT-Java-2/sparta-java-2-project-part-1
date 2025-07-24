package com.sparta.coupang_commerce.domain.purchase.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {

  Long userId;

  List<PurchaseProductRequest> purchaseProducts;
}
