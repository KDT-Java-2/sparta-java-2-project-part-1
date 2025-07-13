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

  ALREADY_EXISTS_EMAIL("이미 사용 중인 이메일입니다"),
  ALREADY_EXISTS_PRODUCT_NAME("상품명이 이미 사용중입니다"),
  NOT_FOUND_CATEGORY("카테고리가 존재하지 않습니다"),
  INVALID_PARENT_CATEGORY("상위 카테고리가 올바르지 않습니다"),
  CATEGORY_HAS_CHILDREN("하위 카테고리가 존재합니다"),
  CATEGORY_HAS_PRODUCTS("카테고리에 등록된 상품이 존재합니다"),
  PRODUCT_IN_COMPLETED_PURCHASE("완료된 주문에 포함된 상품입니다");


  final String message;

}
