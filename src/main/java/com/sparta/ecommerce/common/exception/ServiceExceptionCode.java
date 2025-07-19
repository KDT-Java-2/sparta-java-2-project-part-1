package com.sparta.ecommerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  NOT_FOUND_USER("유저를 찾을 수 없습니다."),
  DUPLICATE_USER_EMAIL("사용중인 이메일입니다."),
  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  DUPLICATE_PROUDCT_NAME("사용중인 상품명입니다."),
  EXISTS_COMPLETE_PURCHASE("주문 데이터가 있는 상품은 삭제할 수 없습니다.");

  final String message;
}
