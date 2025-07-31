package com.socialcommerce.domain.purchase.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseCreateRequest {
  private String shippingAddress;
  private Long couponId;    // (선택적)
}
