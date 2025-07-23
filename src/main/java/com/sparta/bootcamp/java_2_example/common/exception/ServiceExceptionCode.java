package com.sparta.bootcamp.java_2_example.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  ALREADY_EXISTS_EMAIL("이미 존재하는 이메일입니다."),
  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  ALREADY_EXISTS_PRODUCT_NAME("이미 존재하는 상품명입니다.")
  ;

  final String message;
}
