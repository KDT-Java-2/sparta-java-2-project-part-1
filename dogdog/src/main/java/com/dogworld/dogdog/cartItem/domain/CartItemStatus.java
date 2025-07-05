package com.dogworld.dogdog.cartItem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CartItemStatus {

  ACTIVE("활성화"),
  INACTIVE("비활성화"),
  SOLD_OUT("품절")
  ;

  private final String text;
}
