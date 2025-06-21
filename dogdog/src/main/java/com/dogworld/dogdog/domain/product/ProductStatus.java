package com.dogworld.dogdog.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

  SELLING("판매중"),
  SOLD_OUT("품절"),
  HIDDEN("숨김")
  ;

  private final String name;
}
