package com.sparta.commerce_project_01.common.enums.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  USER_NOT_FOUND("이용자를 찾을수 없습니다"),
  USER_ALREADY_EXIST("중복된 이용자입니다"),
  USER_UPDATE_FAIL("이용자정보를 업데이트 하지 못했습니다"),
  USER_DELETE_FAIL("이용자를 삭제하지 못했습니다"),

  PRODUCT_NOT_FOUND("상품를 찾을수 없습니다"),
  PRODUCT_ALREADY_EXIST("중복된 상품입니다"),
  PRODUCT_UPDATE_FAIL("상품정보를 업데이트 하지 못했습니다"),
  PRODUCT_DELETE_FAIL("상품을 삭제하지 못했습니다"),
  ;

  final String message;
}
