package com.sparta.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  NOT_FOUND_DATA("데이터를 찾을 수 없습니다"),
  NOT_FOUND_USER("사용자를 찾을 수 없습니다"),
  OUT_OF_STOCK_PRODUCT("재고가 없습니다"),
  CANNOT_CANCEL("취소가 불가능한 상태입니다"),

  ALREADY_EXISTS_EMAIL("이미 사용 중인 이메일입니다");

  final String message;

}
