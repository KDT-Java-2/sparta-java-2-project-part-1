package com.sparta.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),
  NOT_FOUND_USER("사용자를 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("재고가 부족합니다."),
  EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다."),
  DUPLICATE_PRODUCT_NAME("이미 존재하는 상품명입니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  CANNOT_DELETE_PRODUCT_WITH_ORDERS("주문이 있는 상품은 삭제할 수 없습니다."),
  NOT_FOUND_PARENT_CATEGORY("상위 카테고리를 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY_BY_ID("ID로 카테고리를 찾을 수 없습니다."),
  CATEGORY_CANNOT_BE_ITS_OWN_PARENT("카테고리는 자신의 상위 카테고리가 될 수 없습니다."),
  DUPLICATE_CATEGORY_NAME("이미 존재하는 카테고리 이름입니다."),
  CATEGORY_HAS_CHILDREN("하위 카테고리가 있는 카테고리는 삭제할 수 없습니다."),
  CANNOT_DELETE_CATEGORY_WITH_PRODUCTS("상품이 있는 카테고리는 삭제할 수 없습니다."),

  ;

  final String messsage;
}