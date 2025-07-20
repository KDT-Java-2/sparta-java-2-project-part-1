package com.js.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  USER_NOT_FOUND("유저를 찾을 수 없습니다."),
  DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
  PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다."),
  DUPLICATE_PRODUCT_NAME("이미 존재하는 상품명입니다."),
  INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
  INVALID_PRICE_STOCK("가격 또는 재고 수량은 0 이상이어야 합니다."),
  CATEGORY_NOT_FOUND("요청하신 카테고리가 존재하지 않습니다."),

  ;

  final String message;
}
