package com.sparta.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  // User 관련
  NOT_FOUND_USER("해당 유저를 찾을 수 없습니다."),
  DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
  DUPLICATE_NICKNAME("이미 사용 중인 닉네임입니다."),
  INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),

  // Category 관련
  NOT_FOUND_CATEGORY("해당 카테고리를 찾을 수 없습니다."),
  CATEGORY_HAS_PRODUCTS("카테고리에 등록된 상품이 존재합니다."),
  CATEGORY_HAS_CHILDREN("하위 카테고리가 존재합니다."),
  CATEGORY_SELF_PARENT("자기 자신을 부모 카테고리로 지정할 수 없습니다."),

  // Product 관련
  NOT_FOUND_PRODUCT("존재하지 않는 상품입니다."),
  OUT_OF_STOCK_PRODUCT("주문 가능 수량을 초과하였습니다."),
  PRICE_CANNOT_BE_NEGATIVE("가격은 음수일 수 없습니다."),
  STOCK_CANNOT_BE_NEGATIVE("재고는 음수일 수 없습니다."),

  // Purchase 관련
  CANNOT_DELETE_PRODUCT_WITH_ACTIVE_ORDER("주문이 활성화 되어있어 상품을 삭제할 수 없습니다."),
  NOT_FOUND_PURCHASE("주문 정보를 찾을 수 없습니다."),
  NOT_FOUND_PURCHASE_PRODUCT("특정 상품에 대한 주문 정보를 찾을 수 없습니다."),

  // Seller 관련
  NOT_FOUND_SELLER("판매자 정보를 찾을 수 없습니다."),

  // 공통
  NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),
  INVALID_REQUEST("잘못된 요청입니다."),
  ;
  final String message;

}
