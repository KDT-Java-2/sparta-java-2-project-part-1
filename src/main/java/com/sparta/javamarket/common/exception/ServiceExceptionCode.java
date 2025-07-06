package com.sparta.javamarket.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
  NOT_FOUND_USER("유저를 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  NOT_FOUND_PARENT_CATEGORY("부모 카테고리를 찾을 수 없습니다."),
  NOT_FOUND_DATA("DATA를 찾을 수 없습니다.")


  ;

  final String message;
}